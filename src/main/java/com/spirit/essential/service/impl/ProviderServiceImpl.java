package com.spirit.essential.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.common.utils.TbaUtil;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.spirit.essential.common.exception.ExceptionCode.PARAM_NONE_EXCEPTION;


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

        TbaUtil<ServiceInfo> tba = new TbaUtil();
        byte[] msg = tba.Serialize(entify, 1024);

        log.info("Encode Node ServiceInfo msg len: {}", msg.length);

        try {
            zkClient.createNode(CreateMode.EPHEMERAL ,base, msg);
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
