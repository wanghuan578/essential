package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.net.Socket;

public class SL_RPC_UpdateServer {

	private SL_RPC_UpdateServerSendThread  	m_SendHandler = null;
	
	private SL_RPC_UpdateServerRecvThread 	m_ReadHandler = null;
	
	private SL_RPC_Socket_CommonAPI 	m_SocketComAPI = null;
	
	public SL_RPC_UpdateServer(){

		m_SocketComAPI = new SL_RPC_Socket_CommonAPI(SL_RPC_ServerType.SL_RPC_SERVER_TYPE_UPDATE);
	}
	
	public int handle_open(String addr, int port){
		
		Socket fd = m_SocketComAPI.Connect(addr, port);
		
		if(null != fd){
					
			m_SendHandler = new SL_RPC_UpdateServerSendThread(m_SocketComAPI);
			
			m_ReadHandler = new SL_RPC_UpdateServerRecvThread(m_SocketComAPI);
		
			m_SendHandler.Listern();
			
			m_ReadHandler.Listern();
			
			return 0;
		}
		
		return -1;
	}
	
	public int handle_close(){
		
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

}
