/************************************************************

Description: SL_RPC_SocketManagerIF.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.IOException;

public class SL_RPC_SocketControlHandlerIF {

	private static SL_RPC_SocketControlHandlerIF  m_Instance = null;
	
	private SL_RPC_SocketControlHandlerIF(){
		
		SL_RPC_SocketControlHandler.Instance();
	}
	
	public static synchronized SL_RPC_SocketControlHandlerIF Instance()
	{
		if(null == m_Instance){
			
			m_Instance = new SL_RPC_SocketControlHandlerIF();
		}
		
		return m_Instance;
	}
	
	public boolean Open(String addr, int port){
		
		try
		{
			SL_RPC_SocketControlHandler.Instance().LoginServer_Connect(addr, port);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void Socket_Destroy(int server_type){
		
		SL_RPC_SocketControlHandler.Instance().Destory(server_type);
	}
	
	public void Reset(){
		
		SL_RPC_SocketControlHandler.Instance().Reset();
	}
	
	public SL_RPC_RoomGataServer GetRoomGateHandler(){
		
		return SL_RPC_SocketControlHandler.Instance().GetRoomGateHandler();
	}
	
}
