package com.spirit.essential.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public String register(ServiceInfo service) throws MainStageException {

        log.info("service register: {}", JSON.toJSONString(service, true));

        String base = StringUtils.join(new String [] {ZkConstant.SERVICE,
                        service.getService_name(), ZkConstant.PROVIDERS,
                service.getService_addr().getIp() + ":" + service.getService_addr().getPort()},
                "/");
        log.info("path {}", base);

        zkClient.createNode(CreateMode.EPHEMERAL , base + ZkConstant.WEIGHT, String.valueOf(service.getService_weight()));

        return base;
    }

    @Override
    public void revoke(String node) throws MainStageException {
        zkClient.deleteNode(node, true);
    }
}
