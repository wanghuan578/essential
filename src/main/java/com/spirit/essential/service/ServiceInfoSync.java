package com.spirit.essential.service;

import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;

public interface ServiceInfoSync {
    void sync(ServiceInfo info);
}
