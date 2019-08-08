/************************************************************

Description: SRM_LoginServerRequest.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.message;


import org.apache.thrift.TException;


public class SRM_Test {

	public SRM_Test(String user_name, String passward) {
		
//		SL_RPC_ProtocolFactory<ClientPasswordLoginReqChecksum> factory = new SL_RPC_ProtocolFactory<ClientPasswordLoginReqChecksum>(new ClientPasswordLoginReqChecksum(), 1024, 0);
//
//		factory.GetBuilder().GetBody().logname = user_name;
//		factory.GetBuilder().GetBody().password = passward;
////		factory.GetBuilder().GetBody().client_random = BusinessManager.Instance().GetClientRandom();
////		factory.GetBuilder().GetBody().server_random = BusinessManager.Instance().GetLocalUserInfo().GetLoginInfo().GetServerRandom();
//
//		try
//		{
//			factory.GetBuilder().GetBody().write(factory.GetBuilder().GetTProtocol());
//		}
//		catch (TException e)
//		{
//			e.printStackTrace();
//		}
//
//		SL_RPC_MD5 md5 = new SL_RPC_MD5();
//
//		String check_hex_code = md5.getMD5ofStr(factory.GetBuilder().GetBuffer().GetBytes(), factory.GetBuilder().GetBuffer().Length());
//
//		SL_RPC_ProtocolFactory<ClientPasswordLoginReq> login_factory = new SL_RPC_ProtocolFactory<ClientPasswordLoginReq>(new ClientPasswordLoginReq(), 1024, SL_RPC_CommHead.Size());
//
//		login_factory.GetBuilder().GetBody().logname_type = 0;
//		login_factory.GetBuilder().GetBody().logname = user_name;
////		login_factory.GetBuilder().GetBody().client_random 	= BusinessManager.Instance().GetClientRandom();
////		login_factory.GetBuilder().GetBody().client_mac    	= BusinessManager.Instance().GetLocalUserInfo().GetClientMacAddr();
////		login_factory.GetBuilder().GetBody().client_version = SystemConfigDB.Instance().GetSystemVersion();
//		login_factory.GetBuilder().GetBody().checksum  = check_hex_code;
//
//		try
//		{
//			login_factory.GetBuilder().GetBody().write(login_factory.GetBuilder().GetTProtocol());
//		}
//		catch (TException e)
//		{
//			e.printStackTrace();
//		}
//
//		login_factory.GetBuilder().GetHead().SetType(SL_RPC_Seda_EventType.MT_RPC_SEDA_EVENT_LOGIN_REQ);
//
//		login_factory.GetBuilder().Serialize();
//
//		SL_RPC_SocketControlHandler.Instance().Put_Data(login_factory.GetBuilder().GetBuffer());
	}
}
