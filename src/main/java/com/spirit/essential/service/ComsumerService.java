package com.spirit.essential.service;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import java.util.List;

public interface ComsumerService {
    String getServiceList(String serviceName, List<RouteInfo> serviceInfoList, PathChildrenCacheListener listener) throws MainStageException;
}
