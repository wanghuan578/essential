package com.spirit.essential.thrift.socketserver.rpc.minicore;


import org.apache.thrift.TBase;

public class SL_RPC_UpdateServerHandler {

	private SL_RPC_ByteBuffer m_Message = null;
	
	private RpcProtocolFactory<TBase> m_ProtocolFactory = null;
	
	public SL_RPC_UpdateServerHandler(SL_RPC_ByteBuffer buff){
	
		m_Message = buff;
		
		m_ProtocolFactory = new RpcProtocolFactory<TBase>(m_Message);
		
		Analysis();
	}
	
	public boolean Analysis(){
		
		return m_ProtocolFactory.GetParser().Message_Parser();
	}
	
	private SL_RPC_ByteBuffer GetEvent(){
		
		return m_Message;
	}
	
	private RpcCommonHead GetEventHead(){
		
		return m_ProtocolFactory.GetParser().GetHead();
	}
	
	public int handle_message(){
		
		int ret = 0;
		
		switch(GetEventHead().GetType()){
		
		case RpcEventType.MT_HELLO_NOTIFY: {
			
			//BusinessManager.Instance().CheckUpdateVersion(LoginServer_UpdateType.UPDATE_IN_RUNNING);
		}
			break;
		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_UPDATE_RESOURCE_RESP:{
//
//			SL_RPC_SocketControlHandler.Instance().Destory(SL_RPC_ServerType.SL_RPC_SERVER_TYPE_UPDATE);
//
//			GetEventHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_UPDATE_RESOURCE_RESP);
//
//			SL_RPC_Seda_Event event = new SL_RPC_Seda_Event(GetEvent(), GetEventHead());
//
//			SL_RPC_Seda_Stage.Instance().PushEvent(event);
//		}
//			break;
			
		default:
			break;
		}
		return ret;
	}
}
