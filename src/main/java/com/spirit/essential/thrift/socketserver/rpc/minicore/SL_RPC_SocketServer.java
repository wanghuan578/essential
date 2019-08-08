package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SL_RPC_SocketServer {
	
	private Map<Integer, Socket> m_fd = null;
	
	private int m_CurrentServer = SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_NONE;
	
	public SL_RPC_SocketServer(){
		
		m_fd = new HashMap<Integer, Socket>();
		
		m_fd.put(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_LOGIN_SERVER, (Socket)null);
		
		m_fd.put(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_ROOMGATE_SERVER, (Socket)null);
	}
	
	private void SetCurrentServer(int type){
		
		m_CurrentServer = type;
	}
	
	public int GetCurrentServer(){
		
		return m_CurrentServer;
	}
	
	public void SetLogingServerFD(Socket socket){
		
		SetCurrentServer(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_LOGIN_SERVER);
		
		m_fd.put(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_LOGIN_SERVER, socket);
	}
	
	public Socket GetLogingServerFD(){
		
		return m_fd.get(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_LOGIN_SERVER);
	}
	
	public void SetRoomGateServerFD(Socket socket){
		
		SetCurrentServer(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_ROOMGATE_SERVER);
		
		m_fd.put(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_ROOMGATE_SERVER, socket);
	}
	
	public Socket GetRoomGateServerFD(){
		
		return m_fd.get(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_ROOMGATE_SERVER);
	}
	
	public void Socket_Close(int server_type){
	
		Socket s = m_fd.get(server_type);
		
		if(null != s){
			
			if(s.isConnected()){
				
				try 
				{
					m_fd.get(server_type).close();
					
					m_fd.put(server_type, (Socket)null);
					
					if(SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_ROOMGATE_SERVER == server_type){
						
						m_CurrentServer = SL_RPC_SocketServerType.SL_RPC_SERVER_TYPE_NONE;
					}
					
				} 
				catch (IOException e) 
				{	
					e.printStackTrace();
				}
			}
		}
	}

			

	
}
