package com.spirit.essential.session;

import com.spirit.essential.common.ServiceStatus;
import io.netty.channel.ChannelHandlerContext;

public interface Session {
    int add(ChannelHandlerContext ctx, ServiceStatus status);
    String contain(ChannelHandlerContext ctx);
    int remove(ChannelHandlerContext ctx);
}
