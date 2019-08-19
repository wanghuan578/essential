package com.spirit.essential.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.thrift.idl.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public int register(ServiceInfo info) throws MainStageException {
        log.info("service register: {}", JSON.toJSONString(info, true));
        return 0;
    }
}
