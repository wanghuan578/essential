package com.spirit.essential.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import com.spirit.tsserialize.Exception.TsException;
import com.spirit.tsserialize.core.TsRpcMessageBuilder;
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
    public String register(RouteInfo route) throws MainStageException {

        log.info("service register: {}", JSON.toJSONString(route, true));

        String base = StringUtils.join(new String [] {ZkConstant.SERVICE,
                route.getName(), route.getAddress().getIp() + ":" + route.getAddress().getPort()},"/");

        ServiceInfo entify = new ServiceInfo();
        entify.route = route;

        byte[] msg = null;

        try {
            TsRpcMessageBuilder<ServiceInfo> builder = new TsRpcMessageBuilder<ServiceInfo>(entify, 1024);
            msg = builder.Serialize().OutStream().GetBytes();
        } catch (TsException e) {
            e.printStackTrace();
        }

        try {
            zkClient.createNode(CreateMode.EPHEMERAL ,base, new String(msg));
        }
        catch (MainStageException e) {
            log.error("MainStageException", e);
        }

        return base;
    }

    @Override
    public void revoke(String node) throws MainStageException {
        zkClient.deleteNode(node, true);
    }
}
