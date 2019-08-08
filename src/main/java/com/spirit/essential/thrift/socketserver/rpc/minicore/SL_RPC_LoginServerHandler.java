/*************************************************

Description: SL_RPC_LoginServerHandler

Author: wanghuan. 2013-01-20 

Boxin Technology Corporated Corporation. All Rights Reserved.

**************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;




public class SL_RPC_LoginServerHandler {
	
	private SL_RPC_ByteBuffer m_Event = null;
	
	private SL_RPC_CommHead m_EventHead = null;
	
	public SL_RPC_LoginServerHandler(SL_RPC_ByteBuffer event, SL_RPC_CommHead head){
		
		m_Event = event;
		
		m_EventHead = head;
	}
	
	private SL_RPC_CommHead GetEventHead(){
		
		return m_EventHead;
	}
	
	private SL_RPC_ByteBuffer GetEvent(){
	
		return m_Event;
	}
	
	public void do_message(){
		
		switch(GetEventHead().GetType()){
		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_HELLO_NOTIFY: {
//
//			SL_RPC_SocketControlHandler.Instance().SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_LOGINSERVER_CONNECTED);
//
//			GetEventHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGINSERVER_CONNECTTED);
//
//			SL_RPC_Seda_Event event = new SL_RPC_Seda_Event(GetEvent(), GetEventHead());
//
//			SL_RPC_Seda_Stage.Instance().PushEvent(event);
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_UPDATE_RESOURCE_RESP:{
//
//			SL_RPC_SocketControlHandler.Instance().Destory(SL_RPC_ServerType.SL_RPC_SERVER_TYPE_LOGIN);
//
//			SL_RPC_SocketControlHandler.Instance().SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_NONE);
//
//			GetEventHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_UPDATE_RESOURCE_RESP);
//
//			SL_RPC_Seda_Event event = new SL_RPC_Seda_Event(GetEvent(), GetEventHead());
//
//			SL_RPC_Seda_Stage.Instance().PushEvent(event);
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGIN_RESP:{
//
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//
//			ClientLoginRes resp = new ClientLoginRes();
//			
//			try
//			{
//				resp.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			if(0 == resp.error_code){
//				
//				SL_RPC_SocketControlHandler.Instance().SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_LOGINSERVER_LOGIN_SUCCESSED);
//
//				SL_RPC_Seda_Event event = new SL_RPC_Seda_Event((Object)resp, GetEventHead());
//				
//				SL_RPC_Seda_Stage.Instance().PushEvent(event);
//			}
//			else
//			{
//				SL_RPC_SocketControlHandler.Instance().SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_LOGINSERVER_CONNECTED);
//				
//				SL_RPC_Seda_Event event = new SL_RPC_Seda_Event((Object)resp, GetEventHead());
//				
//				SL_RPC_Seda_Stage.Instance().PushEvent(event);
//			}
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_GET_BALANCE_NOTIFY: {
//
//			SL_RPC_SocketControlHandler.Instance().Destory(SL_RPC_ServerType.SL_RPC_SERVER_TYPE_LOGIN);
//
//			GetEventHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_GET_BALANCE_NOTIFY);
//
//			SL_RPC_Seda_Event event = new SL_RPC_Seda_Event(GetEvent(), GetEventHead());
//
//			SL_RPC_Seda_Stage.Instance().PushEvent(event);
//		}
//			break;
//
//		default:{
//
//			SL_RPC_Seda_Event event = new SL_RPC_Seda_Event(GetEvent(), GetEventHead());
//
//			SL_RPC_Seda_Stage.Instance().PushEvent(event);
//		}
//			break;
			
			
		}
	}
}