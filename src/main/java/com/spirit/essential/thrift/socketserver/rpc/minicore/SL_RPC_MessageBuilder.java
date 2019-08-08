

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.thrift.protocol.TProtocol;


public class SL_RPC_MessageBuilder<TMessageBody>{
	
	private RpcCommonHead m_head = null;
	
	private TMessageBody m_body = null;
	
	private SL_RPC_ByteBuffer m_outProtocol = null;
	
	private TProtocol m_TProtocol = null;
	
	public SL_RPC_MessageBuilder(TMessageBody concrate_body, int buff_size, int offset){
		
		m_head = new RpcCommonHead();
		
//		Class clazz = getMyClass();
//		TMessageBody a = null;
//		try {
//            a = (TMessageBody) clazz.newInstance();
//        } catch (InstantiationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//		m_body = a;
		m_body = concrate_body;
		
		m_outProtocol = new SL_RPC_ByteBuffer(buff_size);
		
		m_TProtocol = new SL_RPC_Thrift_BinaryProtocol(m_outProtocol, offset);
	}

//	public void setProtocol(TProtocol proto) {
//	    m_TProtocol = proto;
//	}
	
//	public Class getMyClass(){
//        System.out.println(this.getClass());
//        //class com.dfsj.generic.UserDaoImpl因为是该类调用的该法，所以this代表它
//
//        //返回表示此 Class 所表示的实体类的 直接父类 的 Type。注意，是直接父类
//        //这里type结果是 com.dfsj.generic.GetInstanceUtil<com.dfsj.generic.User>
//        Type type = getClass().getGenericSuperclass();
//
//        // 判断 是否泛型
//        if (type instanceof ParameterizedType) {
//            // 返回表示此类型实际类型参数的Type对象的数组.
//            // 当有多个泛型类时，数组的长度就不是1了
//            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
//            return (Class) ptype[0];  //将第一个泛型T对应的类返回（这里只有一个）
//        } else {
//            return Object.class;//若没有给定泛型，则返回Object类
//        }
//
//    }

	public RpcCommonHead GetHead(){
		
		return m_head;
	}
	
	public TMessageBody GetBody(){
		
		return m_body;
	}

	public TProtocol GetTProtocol(){
		
		return m_TProtocol;
	}
	
	public SL_RPC_ByteBuffer GetBuffer(){
		
		return m_outProtocol;
	}
	
	public int Serialize(){
	
		int end = m_outProtocol.Length();
		
		m_outProtocol.WriteBufferBegin(0);
		
		m_outProtocol.WriteI32(end);
		m_outProtocol.WriteI16(m_head.GetFlag());
		m_outProtocol.WriteI16(m_head.GetType());
		m_outProtocol.WriteI32(m_head.GetSequence());
		m_outProtocol.WriteI32(m_head.GetSource());
		m_outProtocol.WriteI32(m_head.GetDestination());
		m_outProtocol.WriteI32(m_head.GetCheckSum());
		m_outProtocol.WriteI32(m_head.GetAttach1());
		m_outProtocol.WriteI32(m_head.GetAttach2());
		m_outProtocol.WriteI32(m_head.GetAttach3());
		m_outProtocol.WriteI32(m_head.GetAttach4());
		
		m_outProtocol.WriteBufferBegin(end);
		
		return end;
	}
}
