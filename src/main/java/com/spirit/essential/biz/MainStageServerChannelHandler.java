package com.spirit.essential.biz;

import com.alibaba.fastjson.JSON;
import com.spirit.essential.common.ServiceTypeDef;
import com.spirit.essential.session.ServiceStatus;
import com.spirit.essential.exception.MainStageException;
import com.spirit.essential.rpc.protocol.thrift.*;
import com.spirit.essential.service.ComsumerService;
import com.spirit.essential.service.ProviderService;
import com.spirit.essential.session.SessionFactory;
import com.spirit.tsserialize.core.TsEvent;
import com.spirit.tsserialize.core.TsRpcHead;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
@Sharable
public class MainStageServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ComsumerService comsumerService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodePathChildrenCacheListener nodePathChildrenCacheListener;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

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
        String path;
        if (StringUtils.isNotEmpty(path = sessionFactory.remove(ctx))) {
            try {
                providerService.revoke(path);
            }
            catch (MainStageException e) {
                log.error("MainStageException", e);
            }
        }
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof ClientPasswordLoginReq) {

            log.info("ClientPasswordLoginReq: {}", JSON.toJSONString(msg, true));

            ClientPasswordLoginReq entity = (ClientPasswordLoginReq) msg;

            //todo

            ClientLoginRes body = new ClientLoginRes();
            body.error_code = 0;
            body.error_text = "OK";
            body.session_ticket = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";

            TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_LOGIN_RES);
            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
        else if (msg instanceof ServiceRegisterReq) {

            log.info("ServiceRegisterReq: {}", JSON.toJSONString(msg, true));

            ServiceRegisterRes body = new ServiceRegisterRes();

            try {
                String path = providerService.register(((ServiceRegisterReq) msg).getRoute());

                ServiceStatus status = new ServiceStatus();
                status.setPath(path);
                status.setTimestamp(System.currentTimeMillis()/1000);
                status.setType(ServiceTypeDef.SERVICE_TYPE_PROVIDER);
                sessionFactory.add(ctx, status);

                body.error_code = 0;
                body.error_text = "OK";
            } catch (MainStageException e) {
                log.error("MainStageException", e);
                body.error_code = Integer.valueOf(e.getCode());
                body.error_text = e.getText();
            }

            TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_REGISTER_RES);
            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
        else if (msg instanceof ServiceListReq) {

            log.info("ServiceListReq: {}", JSON.toJSONString(msg, true));

            ServiceListRes body = new ServiceListRes();

            try {
                List<ServiceRouteInfo> serviceInfoList = new LinkedList<>();
                String listenPath = comsumerService.getServiceList(((ServiceListReq) msg).service_name, serviceInfoList, nodePathChildrenCacheListener);

                ServiceStatus status = new ServiceStatus();
                status.setPath(listenPath);
                status.setTimestamp(System.currentTimeMillis()/1000);
                status.setType(ServiceTypeDef.SERVICE_TYPE_CONSUMER);
                sessionFactory.add(ctx, status);

                body.error_code = 0;
                body.error_text = "OK";
                body.info_list = serviceInfoList;
            } catch (MainStageException e) {
                log.error("MainStageException", e);
                body.error_code = Integer.valueOf(e.getCode());
                body.error_text = e.getText();
            }

            TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_LIST_RES);
            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
        else if (msg instanceof ServiceListSyncRes) {

            log.info("ServiceListChangeRes: {}", JSON.toJSONString(msg, true));
        }
    }



}
