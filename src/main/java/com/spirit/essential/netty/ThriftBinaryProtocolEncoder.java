package com.spirit.essential.netty;

import com.spirit.essential.common.Event;
import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcCommonHead;
import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcEventType;
import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TBase;

@Slf4j
public class ThriftBinaryProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

		Event ev = (Event) msg;

		RpcCommonHead head = ev.getHead();
		RpcProtocolFactory<TBase> protocol = null;

		switch(head.GetType()) {

			case RpcEventType.MT_HELLO_NOTIFY: {
				head.SetSource(123);
				head.SetAttach1(123);
				protocol = new RpcProtocolFactory<TBase>((TBase)ev.getBody(), head, 1024, RpcCommonHead.Size());
			}
				break;

			case RpcEventType.MT_CLIENT_LOGIN_RES: {
				head.SetSource(456);
				head.SetAttach1(456);
				protocol = new RpcProtocolFactory<TBase>((TBase)ev.getBody(), head, 1024, RpcCommonHead.Size());
			}
				break;

			default:
				break;
		}

		out.writeBytes(protocol.getBytes(), 0, protocol.serialize());
	}

}
