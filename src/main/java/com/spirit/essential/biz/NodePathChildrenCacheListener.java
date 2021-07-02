package com.spirit.essential.biz;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.rpc.constant.*;
import com.spirit.essential.rpc.protocol.thrift.AddressInfo;
import com.spirit.essential.rpc.protocol.thrift.RouteInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceListSyncNotify;
import com.spirit.essential.session.SessionFactory;
import com.spirit.tba.core.TbaEncryptType;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TbaRpcHead;
import com.spirit.tba.tools.TbaSerializeUtils;
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
        if (null == childData) {
            return;
        }
        byte[] data = childData.getData();
        if (data == null) {
            log.info("childEvent data ==============================> null");
            return;
        }

        TbaSerializeUtils<ServiceInfo> tba = new TbaSerializeUtils();
        ServiceInfo serviceInfo =tba.deserialize(data, ServiceInfo.class);

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

        if (null == contexts) {
            return 0;
        }

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
            TbaRpcHead head = new TbaRpcHead(RpcEventType.MT_SERVICE_LIST_CHANGE_NOTIFY);
            e.write(new TbaEvent(head, notify, 1024, TbaEncryptType.DISABLE));
            e.flush();
        });

        return 0;
    }
}
