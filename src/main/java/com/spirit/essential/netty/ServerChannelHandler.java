package com.spirit.essential.netty;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.Event;
import com.spirit.essential.thrift.idl.ClientLoginRes;
import com.spirit.essential.thrift.idl.ClientPasswordLoginReq;
import com.spirit.essential.thrift.idl.HelloNotify;
import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcCommonHead;
import com.spirit.essential.thrift.socketserver.rpc.minicore.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Sharable
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("client connect");

        HelloNotify body = new HelloNotify();
        body.setServer_random(1000000);
        body.setService_id(888888);
        body.setError_code((short)0);
        body.setError_text("OK");

        RpcCommonHead head = new RpcCommonHead(RpcEventType.MT_HELLO_NOTIFY);

        ctx.write(new Event(head, body));
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client disconnect");
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof ClientPasswordLoginReq) {

            ClientPasswordLoginReq entity = (ClientPasswordLoginReq) msg;
            log.info(JSON.toJSONString(entity, true));

            ClientLoginRes body = new ClientLoginRes();
            body.error_code = 0;
            body.error_text = "OK";
            body.session_ticket = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";

            RpcCommonHead head = new RpcCommonHead(RpcEventType.MT_CLIENT_LOGIN_RES);

            ctx.write(new Event(head, body));
            ctx.flush();
        }
    }


}
