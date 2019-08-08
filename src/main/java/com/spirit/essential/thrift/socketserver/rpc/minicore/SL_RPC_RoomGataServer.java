
package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.net.Socket;
import java.util.Timer;

public class SL_RPC_RoomGataServer {

	private SL_RPC_SendThread  	m_SendHandler = null;
	
	private SL_RPC_RecvThread 	m_ReadHandler = null;
	
	private SL_RPC_Socket_CommonAPI m_SocketComAPI = null;
	
	private Timer m_KeepALiveTimer = null;
	
	public SL_RPC_RoomGataServer(){

		m_SocketComAPI = new SL_RPC_Socket_CommonAPI(SL_RPC_ServerType.SL_RPC_SERVER_TYPE_ROOMGATE);
	}
	
	public int handle_open(String addr, int port){
		
		Socket fd = m_SocketComAPI.Connect(addr, port);
		
		if(null != fd){
			
			SL_RPC_SocketControlHandler.Instance().GetSocketManager().SetCurrentServer(m_SocketComAPI.GetServerType());
			
			m_SendHandler = new SL_RPC_SendThread(m_SocketComAPI);
			
			m_ReadHandler = new SL_RPC_RecvThread(m_SocketComAPI);
			
			m_SendHandler.Listern();
			
			m_ReadHandler.Listern();
			
			return 0;
		}
		
		return -1;
	}
	
	public int handle_close(){
		
		System.out.println("SL_RPC_RoomGataServer - handle_close Destroy");
		
		KeepALive_Destroy();
		
		m_SendHandler.Destory();
		
		m_ReadHandler.Destory();
		
		return 0;
	}
	
	public int handle_message(){
		
		return 0;
	}
	
	public void put_data(SL_RPC_ByteBuffer buff){
		
		m_SendHandler.PutMessageQueue(buff);
	}
	
	public void put_data_ex(SL_RPC_ByteBuffer buff){
		
		m_SendHandler.PutMessageQueueEx(buff);
	}
	
	public void KeepAlive_Start() {

		m_KeepALiveTimer = new Timer();

		m_KeepALiveTimer.schedule(new SL_RPC_KeepALiveTask(), (30 * 1000), (30 * 1000));
	}

	private void KeepALive_Destroy() {

		if (null != m_KeepALiveTimer) 
		{
			m_KeepALiveTimer.cancel();
		}
	}
	
}
