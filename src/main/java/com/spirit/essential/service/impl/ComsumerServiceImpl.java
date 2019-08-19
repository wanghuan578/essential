package com.spirit.essential.service.impl;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.thrift.idl.ServiceInfo;
import com.spirit.essential.zkClient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ComsumerServiceImpl implements ComsumerService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public List<ServiceInfo> getServiceList(String serviceName) throws MainStageException {
        return null;
    }
}
