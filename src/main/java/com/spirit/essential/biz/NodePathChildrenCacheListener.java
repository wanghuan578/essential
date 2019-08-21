package com.spirit.essential.biz;

import com.spirit.essential.common.SyncType;
import com.spirit.essential.rpc.protocol.thrift.AddressInfo;
import com.spirit.essential.rpc.protocol.thrift.ServiceListSyncNotify;
import com.spirit.essential.rpc.protocol.thrift.ServiceRouteInfo;
import com.spirit.essential.session.SessionFactory;
import com.spirit.tsserialize.core.TsEvent;
import com.spirit.tsserialize.core.TsRpcHead;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        switch (pathChildrenCacheEvent.getType()){
            case CHILD_ADDED:
                log.info("新增子节点：{}", childData.getPath());
                nodeSyncNotify(childData.getPath(), SyncType.APPEND);
                break;

            case CHILD_UPDATED:
                log.info("更新子节点：{}", childData.getPath());
                break;

            case CHILD_REMOVED:
                log.info("删除子节点：{}", childData.getPath());
                nodeSyncNotify(childData.getPath(), SyncType.DROP);
                break;
        }
    }

    private int nodeSyncNotify(String path, int type) {

        String sub = path.substring(0, path.lastIndexOf("/"));
        List<ChannelHandlerContext> contexts = sessionFactory.context(sub);

        log.info("ChannelHandlerContext：len: {}", contexts.size());

        String [] hostInfo = path.substring(path.lastIndexOf("/") + 1).split(":");
        String serviceName = path.substring("/services/".length(), path.lastIndexOf("/"));
        ServiceRouteInfo route = new ServiceRouteInfo();
        AddressInfo addr = new AddressInfo();
        addr.ip = hostInfo[0];
        addr.port = Short.valueOf(hostInfo[1]);
        route.addr = addr;
        route.name = serviceName;
        route.weight = 100;
        List<ServiceRouteInfo> infoList = new ArrayList<>();
        infoList.add(route);

        contexts.stream().forEach(e -> {
            ServiceListSyncNotify notify = new ServiceListSyncNotify();
            //notify.service_id = 100000;
            notify.type = type;
            notify.info_list = infoList;
            TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_LIST_CHANGE_NOTIFY);
            e.write(new TsEvent(head, notify, 1024));
            e.flush();
        });
        return 0;
    }
}
