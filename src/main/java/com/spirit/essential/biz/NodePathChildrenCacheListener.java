package com.spirit.essential.biz;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.rpc.constant.*;
import com.spirit.essential.common.utils.TbaUtil;
import com.spirit.essential.rpc.protocol.thrift.AddressInfo;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceListSyncNotify;
import com.spirit.essential.session.SessionFactory;
import com.spirit.tba.Exception.TsException;
import com.spirit.tba.core.TsEvent;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcHead;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Slf4j
public class NodePathChildrenCacheListener implements PathChildrenCacheListener {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {

        ChildData childData = pathChildrenCacheEvent.getData();
        byte[] data = childData.getData();
        if (data == null) {
            log.info("childEvent data ==============================> null");
            return;
        }

        TbaUtil<ServiceInfo> tba = new TbaUtil();
        ServiceInfo serviceInfo =tba.Deserialize(data, ServiceInfo.class);

//        ServiceInfo serviceInfo = null;
//        TsRpcEventParser<ServiceInfo> parser = new TsRpcEventParser<>(data, data.length);
//        try {
//            serviceInfo =  parser.ToEvent(ServiceInfo.class, 0);
//        } catch (TsException e) {
//            log.error("TsException", e);
//        } catch (IllegalAccessException e) {
//            log.error(e.getLocalizedMessage(), e);
//        } catch (InstantiationException e) {
//            log.error(e.getLocalizedMessage(), e);
//        }

        log.info("Decode Node ServiceInfo Info: {}", JSON.toJSONString(serviceInfo, true));

        switch (pathChildrenCacheEvent.getType()){
            case CHILD_ADDED:
                log.info("新增子节点：{}", childData.getPath());
                nodeSyncNotify(childData.getPath(), SyncType.APPEND, serviceInfo);
                break;

            case CHILD_UPDATED:
                log.info("更新子节点：{}", childData.getPath());
                break;

            case CHILD_REMOVED:
                log.info("删除子节点：{}", childData.getPath());
                nodeSyncNotify(childData.getPath(), SyncType.DROP, serviceInfo);
                break;
        }
    }

    private int nodeSyncNotify(String path, String mode, ServiceInfo info) {

        String sub = path.substring(0, path.lastIndexOf("/"));
        List<ChannelHandlerContext> contexts = sessionFactory.context(sub);

        log.info("ChannelHandlerContext：len: {}", contexts.size());

        String [] hostInfo = path.substring(path.lastIndexOf("/") + 1).split(":");
        String serviceName = path.substring("/services/".length(), path.lastIndexOf("/"));

        RouteInfo route = new RouteInfo();
        AddressInfo addr = new AddressInfo();
        addr.ip = hostInfo[0];
        addr.port = Short.valueOf(hostInfo[1]);
        route.address = addr;
        route.name = serviceName;
        route.weight = info.route.weight;

        contexts.stream().forEach(e -> {
            ServiceListSyncNotify notify = new ServiceListSyncNotify();
            notify.mode = mode;
            notify.route = route;
            TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_LIST_CHANGE_NOTIFY);
            e.write(new TsEvent(head, notify, 1024));
            e.flush();
        });

        return 0;
    }
}
