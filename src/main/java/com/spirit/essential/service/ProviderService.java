package com.spirit.essential.service;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.thrift.idl.ServiceInfo;

public interface ProviderService {
    int register(ServiceInfo info) throws MainStageException;
}
