package com.spirit.essential.netty;

import java.util.List;
import com.spirit.essential.thrift.idl.ClientPasswordLoginReq;
import com.spirit.essential.thrift.socketserver.rpc.minicore.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TBase;

@Slf4j
public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
            int msg_len = in.readInt();
            SL_RPC_ByteBuffer msg = new SL_RPC_ByteBuffer(msg_len);
            msg.WriteI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(in.readByte());
                //msg.WriteByte(buf.getByte(i));
            }

            RpcProtocolFactory factory = new RpcProtocolFactory(msg);
            RpcCommonHead header = factory.getHead();

            log.info("Msg Type: {}", header.GetType());

            switch (header.GetType()) {

                case RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ: {
                    RpcProtocolFactory<TBase> protocol = new RpcProtocolFactory<TBase>(msg);
                    ClientPasswordLoginReq entity = new ClientPasswordLoginReq();
                    protocol.deSerialize(entity);
                    out.add(entity);
                    break;
                }
            }
        }

        
    }

}
