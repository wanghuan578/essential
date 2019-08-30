package com.spirit.essential.service.impl;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.service.ServiceInfoSync;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsRpcMessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.spirit.essential.common.exception.ExceptionCode.*;

@Slf4j
@Component
public class ServiceInfoSyncImpl implements ServiceInfoSync {

    @Autowired
    private ZkClient zkClient;

    @Override
    public void sync(ServiceInfo info) throws MainStageException {

        String base = StringUtils.join(new String [] {ZkConstant.SERVICE,
                info.route.getName(), info.route.getAddress().getIp() + ":" + info.route.getAddress().getPort()},"/");

        if (StringUtils.isEmpty(info.route.getName()) || StringUtils.isEmpty(info.route.getAddress().getIp())) {
            throw new MainStageException(PARAM_NONE_EXCEPTION);
        }

        byte msg[] = null;
        try {
            TsRpcMessageBuilder<ServiceInfo> builder = new TsRpcMessageBuilder<ServiceInfo>(info, 1024);
            msg = builder.Serialize().OutStream().GetBytes();
        } catch (TbaException e) {
            throw new MainStageException(SERIALIZE_EXCEPTION);
        }

        try {
            zkClient.setNodeData(base, msg);
        }
        catch (MainStageException e) {
            log.error("MainStageException", e);
        }
    }
}
