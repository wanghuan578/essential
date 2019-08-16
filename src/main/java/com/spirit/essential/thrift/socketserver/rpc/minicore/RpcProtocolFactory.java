
package com.spirit.essential.thrift.socketserver.rpc.minicore;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

import java.lang.reflect.*;

public class RpcProtocolFactory <TMessageBody extends TBase> {

	private SL_RPC_MessageParser m_cmdParser = null;
	
	//private SL_RPC_MessageBuilder<TMessageBody> m_CmdBuilder = null;

	private RpcCommonHead head_;
	private TMessageBody body_;

	private RpcByteBuffer out = null;
	private RpcByteBuffer in = null;

	private TProtocol tProtocol = null;

	public int serialize() throws TException {
		body_.write(tProtocol);
		return SerializeHead();
	}

	public RpcProtocolFactory (TMessageBody body, RpcCommonHead head, int buff_size, int offset) {

		out = new RpcByteBuffer(buff_size);

		tProtocol = new SL_RPC_Thrift_BinaryProtocol(out, offset);

		head_ = head;

		body_ = body;
	}

	public RpcProtocolFactory(RpcByteBuffer msg){

		in = msg;
		m_cmdParser = new SL_RPC_MessageParser(msg.GetBytes(), msg.Length());
	}

	private TMessageBody createT() {

		try {
			Type superClass = getClass().getGenericSuperclass();
			Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
			Class<?> clazz = getRawType(type);
			return (TMessageBody) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Class<?> getRawType(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type rawType = parameterizedType.getRawType();
			return (Class) rawType;
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			return Array.newInstance(getRawType(componentType), 0).getClass();
		} else if (type instanceof TypeVariable) {
			return Object.class;
		} else if (type instanceof WildcardType) {
			return getRawType(((WildcardType) type).getUpperBounds()[0]);
		} else {
			String className = type == null ? "null" : type.getClass().getName();
			throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
		}
	}

	public TMessageBody deSerialize(TMessageBody body) {

		TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(in, RpcCommonHead.Size(), (in.Length() - RpcCommonHead.Size()));

		//body_ = createT();
		body_ = body;

		try {
			body_.read(protocol);
			return body_;
		} catch (TException e) {
			e.printStackTrace();
			return null;
		}
	}

	public RpcCommonHead getHead(){

		head_ = new RpcCommonHead();
		head_.SetLength(in.ReadI32());
		head_.SetFlag(in.ReadI16());
		head_.SetType(in.ReadI16());
		head_.SetSequence(in.ReadI32());
		head_.SetSource(in.ReadI32());
		head_.SetDestination(in.ReadI32());
		head_.SetCheckSum(in.ReadI32());
		head_.SetAttach1(in.ReadI32());
		head_.SetAttach2(in.ReadI32());
		head_.SetAttach3(in.ReadI32());
		head_.SetAttach4(in.ReadI32());
		return head_;
	}

	public int SerializeHead(){

		int end = out.Length();

		out.WriteBufferBegin(0);
		out.WriteI32(end);
		out.WriteI16(head_.GetFlag());
		out.WriteI16(head_.GetType());
		out.WriteI32(head_.GetSequence());
		out.WriteI32(head_.GetSource());
		out.WriteI32(head_.GetDestination());
		out.WriteI32(head_.GetCheckSum());
		out.WriteI32(head_.GetAttach1());
		out.WriteI32(head_.GetAttach2());
		out.WriteI32(head_.GetAttach3());
		out.WriteI32(head_.GetAttach4());
		out.WriteBufferBegin(end);

		return end;
	}

	public RpcByteBuffer getByteBuf() {
		return out;
	}

	public byte[] getBytes() {
		return out.GetBytes();
	}


	
//	public TMessageBody GetBody() {
//	    return m_CmdBuilder.GetBody();
//	}
//	public TProtocol GetProtocol() {
//        return m_CmdBuilder.GetTProtocol();
//    }
	
//	public SL_RPC_MessageBuilder<TMessageBody> GetHead() {
//
//	    return m_CmdBuilder;
//	}


	public SL_RPC_MessageParser GetParser(){

		if(null != m_cmdParser)
	{
		return m_cmdParser;
	}
		else
	{
		return null;
	}
}
	

}
