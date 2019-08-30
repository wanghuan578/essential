package com.spirit.essential.service;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.common.web.request.pojo.ServiceInfoDetailRequest;
import com.spirit.essential.common.web.response.pojo.ApplicationInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.tba.Exception.TsException;
import java.util.List;

public interface ApplicationService {
    List<ApplicationInfo> getApplicationInfo() throws IllegalAccessException, TsException, InstantiationException, MainStageException;
    ServiceInfo getApplicationDetail(ServiceInfoDetailRequest host) throws IllegalAccessException, TsException, InstantiationException, MainStageException;
}