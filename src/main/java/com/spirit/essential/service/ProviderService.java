package com.spirit.essential.service;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceRouteInfo;

public interface ProviderService {
    String register(ServiceRouteInfo info) throws MainStageException;
    void revoke(String node) throws MainStageException;
}
