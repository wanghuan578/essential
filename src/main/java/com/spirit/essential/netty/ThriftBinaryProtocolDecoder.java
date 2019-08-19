package com.spirit.essential.netty;

import java.util.List;
import com.spirit.essential.thrift.idl.ClientPasswordLoginReq;
import com.spirit.essential.thrift.idl.ServiceRegisterReq;
import com.spirit.tsserialize.Exception.TsException;
import com.spirit.tsserialize.core.TsRpcByteBuffer;
import com.spirit.tsserialize.core.TsRpcHead;
import com.spirit.tsserialize.core.TsRpcMessageParser;
import com.spirit.tsserialize.core.TsRpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.spirit.essential.thrift.idl.MessageType.MT_SERVICE_REGISTER_REQ;

@Slf4j
public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
            int msg_len = in.readInt();
            TsRpcByteBuffer msg = new TsRpcByteBuffer(msg_len);
            msg.WriteI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(in.readByte());
            }

            TsRpcMessageParser parser = new TsRpcMessageParser(msg);
            TsRpcHead header = parser.Head();

            log.info("Msg Type: {}", header.GetType());

            try {
                switch (header.GetType()) {

                    case RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ: {
                        TsRpcProtocolFactory<ClientPasswordLoginReq> protocol = new TsRpcProtocolFactory<ClientPasswordLoginReq>(msg);
                        out.add(protocol.Deserialize(ClientPasswordLoginReq.class));
                    }
                        break;

                    case 1200: {
                        TsRpcProtocolFactory<ServiceRegisterReq> protocol = new TsRpcProtocolFactory<ServiceRegisterReq>(msg);
                        out.add(protocol.Deserialize(ServiceRegisterReq.class));
                    }
                        break;

                    default:
                        break;
                }
            }
            catch(TsException e){
                log.error(e.getLocalizedMessage(), e);
            }
            catch(InstantiationException e){
                log.error(e.getLocalizedMessage(), e);
            }
            catch(IllegalAccessException e){
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

        


}
