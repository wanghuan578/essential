package com.spirit.essential.netty;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.Event;
import com.spirit.essential.thrift.idl.ClientLoginRes;
import com.spirit.essential.thrift.idl.ClientPasswordLoginReq;
import com.spirit.essential.thrift.idl.HelloNotify;
import com.spirit.essential.thrift.socketserver.rpc.minicore.SL_RPC_Seda_EventType;
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

        HelloNotify notify = new HelloNotify();
        notify.setServer_random(1000000);
        notify.setService_id(888888);
        notify.setError_code((short)0);
        notify.setError_text("OK");

        ctx.write(new Event(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_HELLO_NOTIFY, notify));
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client disconnect");
    }
    
    @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg){

        if (msg instanceof ClientPasswordLoginReq) {
            ClientPasswordLoginReq entity = (ClientPasswordLoginReq) msg;
            log.info(JSON.toJSONString(entity, true));


            ClientLoginRes resp = new ClientLoginRes();
            resp.error_code = 0;
            resp.error_text = "OK";
            resp.session_ticket = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";

            ctx.write(new Event(SL_RPC_Seda_EventType.MT_CLIENT_LOGIN_RES, resp));
            ctx.flush();
        }



        

    }


}
