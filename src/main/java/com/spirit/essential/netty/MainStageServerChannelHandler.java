package com.spirit.essential.netty;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.thrift.idl.*;
import com.spirit.essential.zkClient.ZkClient;
import com.spirit.tsserialize.core.TsEvent;
import com.spirit.tsserialize.core.TsRpcHead;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Sharable
public class MainStageServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ComsumerService comsumerService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("client connect");

//        zkClient.createNode(CreateMode.EPHEMERAL , "/essential/service/ab/uid");
//        zkClient.createNode(CreateMode.EPHEMERAL , "/essential/service/ab/socketid");
//        zkClient.createNode(CreateMode.EPHEMERAL , "/essential/service/ab/others");
        //zkClient.deleteNode("/new/xyz");

        HelloNotify body = new HelloNotify();
        body.setServer_random(1000000);
        body.setService_id(888888);
        body.setError_code((short)0);
        body.setError_text("OK");

        TsRpcHead head = new TsRpcHead(RpcEventType.MT_COMMON_HELLO_NOTIFY);

        ctx.write(new TsEvent(head, body, 1024));
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

            TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_LOGIN_RES);

            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
        else if (msg instanceof ServiceRegisterReq) {
            log.info(JSON.toJSONString(msg, true));
            ServiceRegisterRes body = new ServiceRegisterRes();
            try {
                providerService.register(((ServiceRegisterReq) msg).getService_info());
                body.error_code = 0;
                body.error_text = "OK";
            } catch (MainStageException e) {
                log.error("MainStageException", e);
                body.error_code = Integer.valueOf(e.getCode());
                body.error_text = e.getText();
            }

            TsRpcHead head = new TsRpcHead(1201);
            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
    }


}
