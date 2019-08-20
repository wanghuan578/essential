package com.spirit.essential.service;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import java.util.List;

public interface ComsumerService {
    String getServiceList(String serviceName, List<ServiceInfo> serviceInfoList, PathChildrenCacheListener listener) throws MainStageException;
}
