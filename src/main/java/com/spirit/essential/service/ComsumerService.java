package com.spirit.essential.service;





import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;

import java.util.List;

public interface ComsumerService {
    List<ServiceInfo> getServiceList(String serviceName) throws MainStageException;
}
