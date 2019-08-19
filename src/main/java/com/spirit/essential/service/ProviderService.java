package com.spirit.essential.service;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;

public interface ProviderService {
    String register(ServiceInfo info) throws MainStageException;
    void revoke(String node) throws MainStageException;
}
