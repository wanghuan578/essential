package com.spirit.essential.biz;

import java.util.List;
import com.spirit.essential.rpc.protocol.thrift.*;
import com.spirit.tba.core.TbaRpcByteBuffer;
import com.spirit.tba.core.TbaRpcEventParser;
import com.spirit.tba.core.TbaRpcHead;
import com.spirit.tba.core.TbaRpcProtocolFactory;
import com.spirit.tba.exception.TbaException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
            int msg_len = in.readInt();
            TbaRpcByteBuffer msg = new TbaRpcByteBuffer(msg_len);
            msg.writeI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.writeByte(in.readByte());
            }

            TbaRpcEventParser parser = new TbaRpcEventParser(msg);
            TbaRpcHead header = parser.Head();

            log.info("Msg Type: {}", header.getType());

            try {
                switch (header.getType()) {

                    case RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ: {
                        TbaRpcProtocolFactory<ClientPasswordLoginReq> protocol = new TbaRpcProtocolFactory<ClientPasswordLoginReq>(msg);
                        out.add(protocol.Decode(ClientPasswordLoginReq.class));
                    }
                        break;

                    case RpcEventType.MT_SERVICE_REGISTER_REQ: {
                        TbaRpcProtocolFactory<ServiceRegisterReq> protocol = new TbaRpcProtocolFactory<ServiceRegisterReq>(msg);
                        out.add(protocol.Decode(ServiceRegisterReq.class));
                    }
                        break;

                    case RpcEventType.MT_SERVICE_LIST_REQ: {
                        TbaRpcProtocolFactory<ServiceListReq> protocol = new TbaRpcProtocolFactory<ServiceListReq>(msg);
                        out.add(protocol.Decode(ServiceListReq.class));
                    }
                        break;

                    case RpcEventType.MT_SERVICE_LIST_CHANGE_RES: {
                        TbaRpcProtocolFactory<ServiceListSyncRes> protocol = new TbaRpcProtocolFactory<ServiceListSyncRes>(msg);
                        out.add(protocol.Decode(ServiceListSyncRes.class));
                    }
                    break;

                    case RpcEventType.MT_SERVICE_QUALITY_SYNC_REQ: {
                        TbaRpcProtocolFactory<ServiceInfo> protocol = new TbaRpcProtocolFactory<ServiceInfo>(msg);
                        out.add(protocol.Decode(ServiceInfo.class));
                    }
                    break;


                    default:
                        break;
                }
            }
            catch(TbaException e){
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
