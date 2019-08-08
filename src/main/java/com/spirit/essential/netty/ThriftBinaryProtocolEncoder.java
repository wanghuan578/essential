package com.spirit.essential.netty;



import com.spirit.essential.common.Event;
import com.spirit.essential.thrift.socketserver.rpc.minicore.SL_RPC_CommHead;
import com.spirit.essential.thrift.socketserver.rpc.minicore.SL_RPC_ProtocolFactory;
import com.spirit.essential.thrift.socketserver.rpc.minicore.SL_RPC_Seda_EventType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.thrift.TBase;

public class ThriftBinaryProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub

		Event ev = (Event) msg;

		SL_RPC_CommHead head = new SL_RPC_CommHead();
		head.SetType(ev.getType());
		SL_RPC_ProtocolFactory<TBase> factory = null;

		switch(ev.getType()) {

			case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_HELLO_NOTIFY: {
				head.SetSource(123);
				head.SetAttach1(123);
				factory = new SL_RPC_ProtocolFactory<TBase>((TBase)ev.getBody(), head, 1024, SL_RPC_CommHead.Size());
			}
				break;

			case SL_RPC_Seda_EventType.MT_CLIENT_LOGIN_RES: {
				head.SetSource(456);
				head.SetAttach1(456);
				factory = new SL_RPC_ProtocolFactory<TBase>((TBase)ev.getBody(), head, 1024, SL_RPC_CommHead.Size());
			}
				break;

			default:
				break;
		}

		out.writeBytes(factory.getByteBuf().GetBytes(), 0, factory.serialize());

		//}
		//else if (msg instanceof ClientLoginRes) {
			
//			SL_RPC_ProtocolFactory<ClientLoginRes> factory = new SL_RPC_ProtocolFactory<ClientLoginRes>((ClientLoginRes) msg, 1024, SL_RPC_CommHead.Size());
//
//			factory.GetBuilder().GetHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGIN_RESP);
//			factory.GetBuilder().GetHead().SetSource(123);
//			factory.GetBuilder().GetHead().SetAttach1(123);
//
//			factory.GetBody().write(factory.GetProtocol());
//			//factory.GetBuilder().Serialize();
//			//byte[] buf = factory.GetBuilder().GetBuffer().GetBytes();
//			//out.writeBytes(buf);
//			out.writeBytes(factory.GetBuilder().GetBuffer().GetBytes(), 0, factory.GetBuilder().Serialize());
			
		//}
		

		
		
		return;
	}

}
