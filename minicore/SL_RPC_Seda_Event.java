

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_Seda_Event {

	private RpcByteBuffer m_EventBody = null;
	
	private RpcCommonHead m_EventHead = null;
	
	private Object m_Reserve = null;
	
	public SL_RPC_Seda_Event(RpcByteBuffer buff, RpcCommonHead event_head){
		
		m_EventBody = buff;
		
		m_EventHead = event_head;
	}
	
	public SL_RPC_Seda_Event(Object obj, RpcCommonHead event_head){
		
		m_Reserve = obj;
		
		m_EventHead = event_head;
	}

	public RpcCommonHead GetEventHead(){
		
		return m_EventHead;
	}
	
	public RpcByteBuffer GetEventBody(){
		
		return m_EventBody;
	}
	
	public Object GetObject(){
		
		return m_Reserve;
	}
}
