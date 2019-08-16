

package com.spirit.essential.thrift.socketserver.rpc.minicore;





public class SL_RPC_Seda_Handler {
	
	private RpcByteBuffer m_Event = null;
	
	private RpcCommonHead m_EventHead = null;
	
	private Object m_Reserve = null;
	
	public SL_RPC_Seda_Handler(SL_RPC_Seda_Event event){
		
		m_Event = event.GetEventBody();
		
		m_EventHead = event.GetEventHead();
		
		m_Reserve = event.GetObject();
	}

	private RpcByteBuffer GetEvent(){
		
		return m_Event;
	}
	
	private RpcCommonHead GetEventHead(){
		
		return m_EventHead;
	}
	
	private Object GetReserve(){
		
		return m_Reserve;
	}
	
	public int handle_message(){
		
		System.out.println("SL_RPC_Seda_Handler - msg_type = " + GetEventHead().GetType());

		switch(GetEventHead().GetType()){
	
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGINSERVER_CONNECTTED: {
			
//			if(BusinessManager.Instance().GetLocalUserInfo().GetLoginInfo().NeedVersionCheck()){
//				
//				System.out.println("SL_RPC_Seda_StageThread - CheckUpdateVersion");
//				
//				BusinessManager.Instance().CheckUpdateVersion(LoginServer_UpdateType.UPDATE_IN_BEGINNING);
//			}
//			else
//			{
//				System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_LOGIN_CONNECTTED");
//				
//				TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//				
//				HelloNotify hello_notify = new HelloNotify();
//				
//				try
//				{
//					hello_notify.read(protocol);
//				} 
//				catch (TException e) 
//				{
//					e.printStackTrace();
//				}
//				
//				BusinessManager.Instance().LoginServerConnectResponse(hello_notify);
//			}
//		}
//			break;
		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGIN_RESP:{
//
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_LOGIN_RESP");
//
////			ClientLoginRes resp = (ClientLoginRes)GetReserve();
////
////		    BusinessManager.Instance().LoginServerResponse(resp, GetEventHead().GetAttach1());
//		}
		      	
//			break;
			

		

			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ROOMGATE_CONNECTTED:
//		{
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ROOMGATE_CONNECTTED");
			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//	
//			HelloNotify hello_notify = new HelloNotify();
//			
//			try
//			{
//				hello_notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().RoomgateServerConnectResponse(hello_notify);
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ROOMGATE_LOGIN_RESP:{
//
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ROOMGATE_RESP");
			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//	
//			CommonRes roomgete_resp = new CommonRes();
//			
//			try
//			{
//				roomgete_resp.read(protocol);		
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().RoomgateServerResponse(roomgete_resp);
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ENTERROOM_RESP:{
//
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ENTERROOM_RESP");
//
//		}
//		    break;
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//	
//			EnterRoomRes roomenter_resp = new EnterRoomRes();
//			
//			try 
//			{
//				roomenter_resp.read(protocol);
//			} 
//			catch (TException e)
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().EnterRoomResponse(roomenter_resp);
//		}
//			break;
			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ENTER_ROOM_NOTIFY:{
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			UserInfo notify = new UserInfo();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().EnterRoomNotify(notify);	
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_KEEPLIVE_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_KEEPLIVE_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//	
//			KeepAliveRes keeplive_resp = new KeepAliveRes();
//			
//			try 
//			{
//				keeplive_resp.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().RoomgateKeepLiveResponse(keeplive_resp);	
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_KICKUSER_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_KICKUSER_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			ToSingleUserOperateReq notify = new ToSingleUserOperateReq();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().KickUserNotify(notify);
//			
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_USERLIST_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_USERLIST_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetUserListRes resp = new GetUserListRes();
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
//			BusinessManager.Instance().UserListNotify(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SENDCHAT_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SENDCHAT_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			CommonRes resp = new CommonRes();
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
//			BusinessManager.Instance().SendChatResponse(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_CHAT_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_CHAT_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			ChatReq notify = new ChatReq();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().ChatNotify(GetEventHead(), notify);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_BIGGIFT_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_BIGGIFT_NOTIFY");
//		
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ROOMINFO_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ROOMINFO_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetRoomInfoRes resp = new GetRoomInfoRes();
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
//			BusinessManager.Instance().RoomInfoNotify(resp);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SERVICE_IP_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SERVICE_IP_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetServiceIPRes resp = new GetServiceIPRes();
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
//			BusinessManager.Instance().ServerIPResp(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SIEGE_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SIEGE_NOTIFY");
//		}
//			break;
//	
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SPECIAL_LIST_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SPECIAL_LIST_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetSpecialListRes resp = new GetSpecialListRes();
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
//			BusinessManager.Instance().SpecialListNotify(resp);
//		}
//			break;
//	
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_MIC_LIST_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_MIC_LIST_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetMicUserListRes resp = new GetMicUserListRes();
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
//			BusinessManager.Instance().MicUserListNotify(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LEAVE_ROOM_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_MIC_LIST_NOTIFY");
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LEAVE_ROOM_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_LEAVE_ROOM_NOTIFY");
//			
//			BusinessManager.Instance().LeaveRoomNotify(GetEventHead().GetSource());
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ROLE_PERMISSION_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ROLE_PERMISSION_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GetRolePermissionRes resp = new GetRolePermissionRes();
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
//			BusinessManager.Instance().RolePermissionResp(resp);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_GIFT_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_GIFT_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			CommonRes resp = new CommonRes();
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
//			BusinessManager.Instance().SendGiftResp(resp);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_GIFT_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - SL_RPC_SEDA_EVENT_GIFT_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			GiftReq resp = new GiftReq();
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
//			BusinessManager.Instance().GiftNotify(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_UPDATE_USERINFO_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_UPDATE_USERINFO_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			CommonRes resp = new CommonRes();
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
//			BusinessManager.Instance().UpdateUserInfoResp(resp);
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SITEMICUSERLIST_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SITEMICUSERLIST_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			SiteMicupUserList resp = new SiteMicupUserList();
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
//			BusinessManager.Instance().SiteMicUserListResp(resp);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SITEMIC_UP_NOTIFY:
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_SITEMIC_DOWN_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_SITEMIC_STATE_CHANGE_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			SiteMicupUserInfo notify = new SiteMicupUserInfo();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().SiteMicStateChange(notify, GetEventHead().GetType());
//		}
//			break;
//		
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_BALANCE_CHANGE_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_BALANCE_CHANGE_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			BalanceChangeNotify resp = new BalanceChangeNotify();
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
//			BusinessManager.Instance().BalanceChangeNotify(resp);
//		}
//			break;
//
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_CHECK_PASSWARD_RESP:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_CHECK_PASSWARD_RESP");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			CommonRes resp = new CommonRes();
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
//			BusinessManager.Instance().CheckPasswardResponse(resp);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_MEDIA_UP_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_MEDIA_UP_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			MediaReq notify = new MediaReq();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().MediaUpNotify(GetEventHead(), notify);
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_MEDIA_DOWN_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_MEDIA_DOWN_NOTIFY");
//			
//			BusinessManager.Instance().MediaDownNotify(GetEventHead());
//		}
//			break;
//			
//		case SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_ADD_ROOM_MANAGER_LIST_NOTIFY:{
//			
//			System.out.println("SL_RPC_Seda_StageThread - MT_RPC_SEDA_EVENT_ADD_ROOM_MANAGER_LIST_NOTIFY");
//			
//			TProtocol protocol = new SL_RPC_Thrift_BinaryProtocol(GetEvent(), SL_RPC_CommHead.Size(), (GetEvent().Length() - SL_RPC_CommHead.Size()));
//			
//			ToSingleUserOperateReq notify = new ToSingleUserOperateReq();
//			
//			try 
//			{
//				notify.read(protocol);
//			} 
//			catch (TException e) 
//			{
//				e.printStackTrace();
//			}
//			
//			BusinessManager.Instance().AddManagerNotify(notify);
//		}
//			break;
			
		default:
			break;
		}
		
		return 0;
	}


}
