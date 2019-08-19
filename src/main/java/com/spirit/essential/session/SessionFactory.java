package com.spirit.essential.session;

import com.spirit.essential.common.ServiceConsumerStatus;
import com.spirit.essential.common.ServiceStatus;
import com.spirit.essential.zkClient.ZkClient;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionFactory {

    private final Map<ChannelHandlerContext, ServiceStatus> providerSessionMap = new ConcurrentHashMap<>();
    //private final Map<ChannelHandlerContext, ServiceConsumerStatus> consumerSessionMap = new ConcurrentHashMap<>();
    private final Map<String, List<ChannelHandlerContext>> pathChannelHandlerContextListRelationship = new ConcurrentHashMap<>();


    public int add(ChannelHandlerContext ctx, ServiceStatus status) {
        log.info("add ctx: {}", ctx);
        providerSessionMap.put(ctx, status);

//        List<ChannelHandlerContext> list = pathChannelHandlerContextListRelationship.get(status.getListenPath());
//        if (!list.contains(ctx)) {
//            list.add(ctx);
//        }

        return 0;
    }

    public String contain(ChannelHandlerContext ctx) {
        if (providerSessionMap.containsKey(ctx)) {
            return providerSessionMap.get(ctx).getPath();
        }
        return null;
    }

    public int remove(ChannelHandlerContext ctx) {
        log.info("remove ctx: {}", ctx);
        providerSessionMap.remove(ctx);
        return 0;
    }


}
