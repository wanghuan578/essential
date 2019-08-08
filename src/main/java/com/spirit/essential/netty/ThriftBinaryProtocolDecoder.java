package com.spirit.essential.netty;

import java.util.List;


import com.spirit.essential.thrift.idl.ClientPasswordLoginReq;
import com.spirit.essential.thrift.socketserver.rpc.minicore.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
            int msg_len = in.readInt();
            SL_RPC_ByteBuffer msg = new SL_RPC_ByteBuffer(msg_len);
            msg.WriteI32(msg_len);
            
            ByteBuf buf = in.readBytes(msg_len - 4);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(buf.getByte(i));
            }

            SL_RPC_MainStageHandler mainStage = new SL_RPC_MainStageHandler(msg);
            mainStage.Analysis();
            SL_RPC_CommHead header = mainStage.GetEventHead();

            switch (header.GetType()) {

                case SL_RPC_Seda_EventType.MT_CLIENT_PASSWORD_LOGIN_REQ: {
                    TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(msg, SL_RPC_CommHead.Size(), (msg.Length() - SL_RPC_CommHead.Size()));
                    ClientPasswordLoginReq entity = new ClientPasswordLoginReq();
                    try {
                        entity.read(protocol);
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                    out.add(entity);
                    break;
                }
            }
        }

        
    }

}
