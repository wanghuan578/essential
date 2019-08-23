package com.spirit.essential.biz;


import com.alibaba.fastjson.JSON;
import com.spirit.essential.rpc.protocol.thrift.*;
import com.spirit.tsserialize.core.TsEvent;
import com.spirit.tsserialize.core.TsRpcHead;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


@Sharable
public class ClientHandler extends SimpleChannelInboundHandler{
    
	private final String IP = "localhost";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
    	
    	if (msg instanceof HelloNotify) {

    		HelloNotify notify = (HelloNotify) msg;
    		System.out.println(JSON.toJSONString(notify, true));

    		ClientPasswordLoginReq req = new ClientPasswordLoginReq();
    		req.setCheck_sum("112211221121122122trfgft4rfrtfghtrgy");
    		req.setClient_mac("112233445566");
    		req.setClient_version("1.0.0.0");

			TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ);
			ctx.write(new TsEvent(head, req, 1024));
			ctx.flush();
    	}
    	else if (msg instanceof ClientLoginRes) {

    		ClientLoginRes loginResp = (ClientLoginRes) msg;
    		System.out.println("ClientLoginRes: " + JSON.toJSONString(loginResp, true));

			ServiceRegisterReq req = new ServiceRegisterReq();
			AddressInfo addr =  new AddressInfo();
			addr.ip = IP;
			addr.port = 8080;
			RouteInfo info = new RouteInfo();
			info.name = "translate";
			info.weight = 99;
			info.address = addr;
			req.route = info;

			TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_REGISTER_REQ);
			ctx.write(new TsEvent(head, req, 1024));
			ctx.flush();
    	}
		else if (msg instanceof ServiceRegisterRes) {

			ServiceRegisterRes res = (ServiceRegisterRes) msg;
			System.out.println("ServiceRegisterRes: " + JSON.toJSONString(res, true));

			ServiceListReq req = new ServiceListReq();
			req.service_name = "translate";

			TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_LIST_REQ);
			ctx.write(new TsEvent(head, req, 1024));
			ctx.flush();

		}else if (msg instanceof ServiceListRes) {

			ServiceListRes res = (ServiceListRes) msg;
			System.out.println("ServiceListRes: " + JSON.toJSONString(res, true));
		}
		else if (msg instanceof ServiceListSyncNotify) {

			ServiceListSyncNotify notify = (ServiceListSyncNotify) msg;
			System.out.println("ServiceListChangeNotify: " + JSON.toJSONString(notify, true));

			ServiceListSyncRes req = new ServiceListSyncRes();

			TsRpcHead head = new TsRpcHead(RpcEventType.MT_SERVICE_LIST_CHANGE_RES);
			ctx.write(new TsEvent(head, req, 1024));
			ctx.flush();
		}
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub

	}


}
