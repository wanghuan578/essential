/************************************************************

Description: SL_Seda_Event.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_Seda_Event {

	private SL_RPC_ByteBuffer m_EventBody = null;
	
	private SL_RPC_CommHead m_EventHead = null;
	
	private Object m_Reserve = null;
	
	public SL_RPC_Seda_Event(SL_RPC_ByteBuffer buff, SL_RPC_CommHead event_head){
		
		m_EventBody = buff;
		
		m_EventHead = event_head;
	}
	
	public SL_RPC_Seda_Event(Object obj, SL_RPC_CommHead event_head){
		
		m_Reserve = obj;
		
		m_EventHead = event_head;
	}

	public SL_RPC_CommHead GetEventHead(){
		
		return m_EventHead;
	}
	
	public SL_RPC_ByteBuffer GetEventBody(){
		
		return m_EventBody;
	}
	
	public Object GetObject(){
		
		return m_Reserve;
	}
}
