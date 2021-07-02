package com.spirit.essential.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaSerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.spirit.essential.common.exception.ExceptionCode.PARAM_NONE_EXCEPTION;
import static com.spirit.essential.common.exception.ExceptionCode.SERIALIZE_EXCEPTION;


@Slf4j
@Component
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public String register(RouteInfo route) throws MainStageException {

        log.info("service register: {}", JSON.toJSONString(route, true));

        if (StringUtils.isEmpty(route.getName()) || StringUtils.isEmpty(route.getAddress().getIp())) {
            throw new MainStageException(PARAM_NONE_EXCEPTION);
        }

        String base = StringUtils.join(new String [] {ZkConstant.SERVICE,
                route.getName(), route.getAddress().getIp() + ":" + route.getAddress().getPort()},"/");

        ServiceInfo entify = new ServiceInfo();
        entify.route = route;

        TbaSerializeUtils<ServiceInfo> tba = new TbaSerializeUtils();
        byte[] msg = new byte[0];
        try {
            msg = tba.serialize(entify, 1024);
        } catch (TbaException e) {
            throw new MainStageException(SERIALIZE_EXCEPTION);
        }

        log.info("Encode Node ServiceInfo msg len: {}", msg.length);

        zkClient.createNode(CreateMode.EPHEMERAL ,base, msg);

        return base;
    }

    @Override
    public void revoke(String node) throws MainStageException {
        zkClient.deleteNode(node, true);
    }
}
