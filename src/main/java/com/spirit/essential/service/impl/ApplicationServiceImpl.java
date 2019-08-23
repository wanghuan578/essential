package com.spirit.essential.service.impl;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.common.utils.TbaUtil;
import com.spirit.essential.common.web.request.pojo.ServiceInfoDetailRequest;
import com.spirit.essential.common.web.response.pojo.ApplicationInfo;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.service.ApplicationService;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import com.spirit.tba.Exception.TsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

import static com.spirit.essential.common.exception.ExceptionCode.NODE_SERVICE_DATA_EMPTY_EXCEPTION;

@Slf4j
@Component
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public List<ApplicationInfo> getApplicationInfo() throws IllegalAccessException, TsException, InstantiationException, MainStageException {

        List<ApplicationInfo> applicationInfoList = new ArrayList<>();

        String path = ZkConstant.SERVICE;
        List<String> applicationList = zkClient.getChildren(path);
        for (String application : applicationList) {

            String applicationPath = path + "/" + application;
            List<String> serviceList = zkClient.getChildren(applicationPath);
            if (!CollectionUtils.isEmpty(serviceList)) {
                List<RouteInfo> routeInfoList = new ArrayList<>();
                for (String service : serviceList) {
                    byte[] msg = zkClient.getNodeData(applicationPath + "/" + service);
                    if (msg != null) {
                        TbaUtil<ServiceInfo> tba = new TbaUtil();
                        ServiceInfo serviceInfo = tba.Deserialize(msg, ServiceInfo.class);
                        routeInfoList.add(serviceInfo.route);
                    }
                    else {
                        throw new MainStageException(NODE_SERVICE_DATA_EMPTY_EXCEPTION);
                    }
                }
                ApplicationInfo applicationInfo = new ApplicationInfo();
                applicationInfo.setApplicationName(application);
                applicationInfo.setRouteInfoList(routeInfoList);
                applicationInfoList.add(applicationInfo);
            }
        }

        return applicationInfoList;
    }

    @Override
    public ServiceInfo getApplicationDetail(ServiceInfoDetailRequest req) throws IllegalAccessException, TsException, InstantiationException, MainStageException {
        String path = StringUtils.join(new String[] {ZkConstant.SERVICE, req.getApplication(), req.getHost() + ":" + req.getPort()}, "/");
        log.info("host: {}", path);
        byte[] msg = zkClient.getNodeData(path);
        if (msg != null) {
            TbaUtil<ServiceInfo> tba = new TbaUtil();
            return tba.Deserialize(msg, ServiceInfo.class);
        }
        else {
            throw new MainStageException(NODE_SERVICE_DATA_EMPTY_EXCEPTION);
        }
    }
}
