package com.spirit.essential.session;

import com.spirit.essential.common.ServiceProviderStatus;
import com.spirit.essential.common.ServiceStatus;
import io.netty.channel.ChannelHandlerContext;

public interface Session {
    int add(ChannelHandlerContext ctx, ServiceProviderStatus status);
    String contain(ChannelHandlerContext ctx);
    int remove(ChannelHandlerContext ctx);
}
