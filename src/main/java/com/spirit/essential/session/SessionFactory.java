package com.spirit.essential.session;

import com.spirit.essential.common.ServiceStatus;
import com.spirit.essential.zkClient.ZkClient;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionFactory implements Session {

//    @Autowired
//    private ZkClient zkClient;

    private Map<ChannelHandlerContext, ServiceStatus> sessionMap = new ConcurrentHashMap<>();

    @Override
    public int add(ChannelHandlerContext ctx, ServiceStatus status) {
        log.info("add ctx: {}", ctx);
        sessionMap.put(ctx, status);
        return 0;
    }

    @Override
    public String contain(ChannelHandlerContext ctx) {
        if (sessionMap.containsKey(ctx)) {
            return sessionMap.get(ctx).getPath();
        }
        return null;
    }

    @Override
    public int remove(ChannelHandlerContext ctx) {
        log.info("remove ctx: {}", ctx);
        sessionMap.remove(ctx);
        return 0;
    }
}
