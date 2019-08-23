package com.spirit.essential.biz;

import java.util.List;
import com.spirit.essential.rpc.protocol.thrift.*;
import com.spirit.tsserialize.Exception.TsException;
import com.spirit.tsserialize.core.TsRpcByteBuffer;
import com.spirit.tsserialize.core.TsRpcEventParser;
import com.spirit.tsserialize.core.TsRpcHead;

import com.spirit.tsserialize.core.TsRpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
        	//in.markReaderIndex();

            int msg_len = in.readInt();
            TsRpcByteBuffer msg = new TsRpcByteBuffer(msg_len);
            msg.WriteI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(in.readByte());
            }

            TsRpcEventParser parser = new TsRpcEventParser(msg);
            TsRpcHead header = parser.Head();

            //log.info("Msg Type: {}", header.GetType());

            try {
                switch (header.GetType()) {

                    case RpcEventType.MT_COMMON_HELLO_NOTIFY: {
                        TsRpcProtocolFactory<HelloNotify> protocol = new TsRpcProtocolFactory<HelloNotify>(msg);
                        out.add(protocol.Decode(HelloNotify.class));
                    }
                    break;

                    case RpcEventType.MT_CLIENT_LOGIN_RES: {
                        TsRpcProtocolFactory<ClientLoginRes> protocol = new TsRpcProtocolFactory<ClientLoginRes>(msg);
                        out.add(protocol.Decode(ClientLoginRes.class));
                    }
                    break;

                    case RpcEventType.MT_SERVICE_REGISTER_RES: {
                        TsRpcProtocolFactory<ServiceRegisterRes> protocol = new TsRpcProtocolFactory<ServiceRegisterRes>(msg);
                        out.add(protocol.Decode(ServiceRegisterRes.class));
                    }
                    break;

                    case RpcEventType.MT_SERVICE_LIST_RES: {
                        TsRpcProtocolFactory<ServiceListRes> protocol = new TsRpcProtocolFactory<ServiceListRes>(msg);
                        out.add(protocol.Decode(ServiceListRes.class));
                    }
                    break;

                    case RpcEventType.MT_SERVICE_LIST_CHANGE_NOTIFY: {
                        TsRpcProtocolFactory<ServiceListSyncNotify> protocol = new TsRpcProtocolFactory<ServiceListSyncNotify>(msg);
                        out.add(protocol.Decode(ServiceListSyncNotify.class));
                    }
                    break;

                    default:
                        break;
                }
            }
            catch(TsException e){
                //log.error(e.getLocalizedMessage(), e);
            }
            catch(InstantiationException e){
                //log.error(e.getLocalizedMessage(), e);
            }
            catch(IllegalAccessException e){
                //log.error(e.getLocalizedMessage(), e);
            }



        }
        
      

        
    }

}
