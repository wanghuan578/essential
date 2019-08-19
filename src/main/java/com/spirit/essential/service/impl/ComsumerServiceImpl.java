package com.spirit.essential.service.impl;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceAddr;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ComsumerServiceImpl implements ComsumerService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public String getServiceList(String serviceName, List<ServiceInfo> serviceInfoList) throws MainStageException {

        String base = StringUtils.join(new String [] {ZkConstant.SERVICE, serviceName, ZkConstant.PROVIDERS}, "/");
        log.info("getServiceList path {}", base);

        List<String> list = zkClient.getChildren(base);

        //List<ServiceInfo> serviceList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (String s : list) {
                String index[] = s.split(":");
                ServiceInfo item = new ServiceInfo();
                item.service_name = serviceName;
                ServiceAddr addr = new ServiceAddr();
                addr.ip = index[0];
                addr.port = Short.valueOf(index[1]);
                item.service_addr = addr;
                serviceInfoList.add(item);
            }
        }
        else {
            throw new MainStageException("list null");
        }

        return base;
    }
}
