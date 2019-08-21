package com.spirit.essential.biz;

import com.spirit.tsserialize.Exception.TsException;
import com.spirit.tsserialize.core.TsEvent;
import com.spirit.tsserialize.core.TsRpcHead;
import com.spirit.tsserialize.core.TsRpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TBase;

@Slf4j
public class ThriftBinaryProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

		TsEvent ev = (TsEvent) msg;

		try {
			TsRpcHead head = ev.getHead();
			TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLen());
			int len = protocol.Serialize();
			out.writeBytes(protocol.OutStream().GetBytes(), 0, len);
		}
		catch (TsException e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

}
