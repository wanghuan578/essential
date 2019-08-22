package com.spirit.essential.service.impl;

import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.AddressInfo;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.essential.zkClient.ZkConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;

import static com.spirit.essential.exception.ErrorType.SERVICE_LIST_EMPTY_EXCEPTION;

@Slf4j
@Component
public class ComsumerServiceImpl implements ComsumerService {

    @Autowired
    private ZkClient zkClient;

    @Override
    public String getServiceList(String serviceName, List<RouteInfo> serviceInfoList, PathChildrenCacheListener listener) throws MainStageException {

        String path = StringUtils.join(new String [] {ZkConstant.SERVICE, serviceName}, "/");

        log.info("getServiceList path {}", path);

        String listenPath = StringUtils.join(new String [] {ZkConstant.SERVICE, serviceName}, "/");
        List<String> list = zkClient.getChildren(path);

        log.info("listen path; {}",listenPath);

        try {
            zkClient.watchPathChildrenExclusive(listenPath, listener);
        }
        catch (MainStageException e) {
            log.info("忽略：{}", e.getText());
        }

        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(e -> {
                String index[] = e.split(":");
                RouteInfo item = new RouteInfo();
                item.name = serviceName;
                AddressInfo addr = new AddressInfo();
                addr.ip = index[0];
                addr.port = Short.valueOf(index[1]);
                item.address = addr;
                serviceInfoList.add(item);
            });
        }
        else {
            throw new MainStageException(SERVICE_LIST_EMPTY_EXCEPTION);
        }

        return path;
    }
}
