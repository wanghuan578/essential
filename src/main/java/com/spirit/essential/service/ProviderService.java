package com.spirit.essential.service;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;

public interface ProviderService {
    String register(RouteInfo info) throws MainStageException;
    void revoke(String node) throws MainStageException;
}
