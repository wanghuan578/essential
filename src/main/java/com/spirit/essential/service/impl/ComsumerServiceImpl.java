package com.spirit.essential.service.impl;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.AddressInfo;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaSerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;

import static com.spirit.essential.common.exception.ExceptionCode.DESERIALIZE_EXCEPTION;
import static com.spirit.essential.common.exception.ExceptionCode.SERVICE_LIST_EMPTY_EXCEPTION;


@Slf4j
@Component
public class ComsumerServiceImpl implements ComsumerService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public String getServiceList(String serviceName, List<RouteInfo> serviceInfoList, PathChildrenCacheListener listener) throws MainStageException, IllegalAccessException, InstantiationException {

        String path = StringUtils.join(new String [] {ZkConstant.SERVICE, serviceName}, "/");

        log.info("getServiceList path {}", path);

        //String listenPath = StringUtils.join(new String [] {ZkConstant.SERVICE, serviceName}, "/");
        List<String> list = zkClient.getChildren(path);

        log.info("listen path; {}",path);

        try {
            zkClient.watchPathChildrenExclusive(path, listener);
        }
        catch (MainStageException e) {
            log.info("忽略：{}", e.getText());
        }

        if (!CollectionUtils.isEmpty(list)) {
            for (String node : list) {

                String tmp = path + "/" + node;
                byte[] data = zkClient.getNodeData(tmp);
                TbaSerializeUtils<ServiceInfo> tba = new TbaSerializeUtils();
                ServiceInfo serviceInfo = null;
                try {
                    serviceInfo = tba.deserialize(data, ServiceInfo.class);
                } catch (TbaException e) {
                    throw new MainStageException(DESERIALIZE_EXCEPTION);
                }

                RouteInfo item = new RouteInfo();
                item.name = serviceInfo.route.getName();
                item.weight = serviceInfo.getRoute().getWeight();
                item.address = serviceInfo.getRoute().getAddress();
                serviceInfoList.add(item);
            }
        }
        else {
            throw new MainStageException(SERVICE_LIST_EMPTY_EXCEPTION);
        }



//        if (!CollectionUtils.isEmpty(list)) {
//            list.stream().forEach(e -> {
//                String index[] = e.split(":");
//                RouteInfo item = new RouteInfo();
//                item.name = serviceName;
//                AddressInfo addr = new AddressInfo();
//                addr.ip = index[0];
//                addr.port = Short.valueOf(index[1]);
//                item.address = addr;
//                serviceInfoList.add(item);
//            });
//        }
//        else {
//            throw new MainStageException(SERVICE_LIST_EMPTY_EXCEPTION);
//        }

        return path;
    }
}
