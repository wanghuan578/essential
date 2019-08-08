

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.IOException;



public class SL_RPC_SocketControlHandler {

	private static SL_RPC_SocketControlHandler m_Instance = null;
	
	private SL_RPC_LoginServer m_LoginServerHandler = null;
	
	private SL_RPC_UpdateServer m_UpdateServerHandler = null;
	
	private SL_RPC_RoomGataServer m_RoomGateHandler = null;
	
	private SL_RPC_SocketFactory m_SocketManger = null;
	
	private SL_RPC_StateMachine m_StateMachine = null;
	
	public static synchronized SL_RPC_SocketControlHandler Instance(){
		
		if(null == m_Instance){
			
			m_Instance = new SL_RPC_SocketControlHandler();
		}
		
		return m_Instance;
	}
	
	public void Reset(){
		
		m_Instance = new SL_RPC_SocketControlHandler();
	}
	
	private SL_RPC_SocketControlHandler(){
		
		m_SocketManger = new SL_RPC_SocketFactory();
		
		m_StateMachine = new SL_RPC_StateMachine();
	};

	public int LoginServer_Connect(String addr, int port) throws IOException{
		
		System.out.println("SL_RPC_SocketControlHandler - LoginServer TCPClient Open");
		
		m_StateMachine.SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_LOGINSERVER_CONNECTTING);

		m_LoginServerHandler = new SL_RPC_LoginServer();

		return m_LoginServerHandler.handle_open(addr, port);
	}
	
	public int RoomgateServer_Connect(String addr, int port) throws IOException{
		
		System.out.println("SL_RPC_SocketControlHandler - RoomGate TCPClient Open");
		
		m_StateMachine.SetState(SL_RPC_State.SL_RPC_SOCKETSTATE_ROOMGATESERVER_CONNECTTING);
		
		m_RoomGateHandler = new SL_RPC_RoomGataServer();
		
		return m_RoomGateHandler.handle_open(addr, port);
	}
	
	public int UpdateServer_Connect(String addr, int port) throws IOException{
		
		System.out.println("SL_RPC_SocketControlHandler - UpdateServer TCPClient Open");
		
		m_UpdateServerHandler = new SL_RPC_UpdateServer();

		return m_UpdateServerHandler.handle_open(addr, port);
	}

	public SL_RPC_SocketFactory GetSocketManager(){
	
		return m_SocketManger;
	}
	
	public int Put_DataEx(SL_RPC_ByteBuffer buff){
		
		int ret = 0;
		
		switch(m_SocketManger.GetCurrentServer()){
		
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_LOGIN:{
			
			m_LoginServerHandler.put_data(buff);
		}
			break;
			
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_ROOMGATE:{
			
			m_RoomGateHandler.put_data_ex(buff);
		}
			break;
			
		default:
			break;
		}
		
		return ret;
	}
	
	public int Put_Data(SL_RPC_ByteBuffer buff) {
		
		int ret = 0;
		
		switch(m_SocketManger.GetCurrentServer()){
		
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_LOGIN:{
			
			m_LoginServerHandler.put_data(buff);
		}
			break;
			
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_ROOMGATE:{
			
			m_RoomGateHandler.put_data(buff);
		}
			break;
			
		default:
			break;
		}
		
		return ret;
	}
	
	public SL_RPC_RoomGataServer GetRoomGateHandler(){
		
		return m_RoomGateHandler;
	}
	
	public SL_RPC_UpdateServer GetUpdateServerHandler(){
		
		return m_UpdateServerHandler;
	}
	
	public void Destory(int server_type){
		
		switch(server_type){
		
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_LOGIN:{
			
			if(null != m_LoginServerHandler){
			
				m_LoginServerHandler.handle_close();
				
				m_LoginServerHandler = null;
			}
		}
			break;
		
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_ROOMGATE:{
			
			if(null != m_RoomGateHandler){
			
				m_RoomGateHandler.handle_close();
				
				m_RoomGateHandler = null;
			}
		}
			break;
			
		case SL_RPC_ServerType.SL_RPC_SERVER_TYPE_UPDATE:{
			
			if(null != m_UpdateServerHandler){
			
				m_UpdateServerHandler.handle_close();
				
				m_UpdateServerHandler = null;
			}
		}
			break;
		
		default:
			break;
		}
	}
	
	public int GetState(){
		
		return m_StateMachine.GetState();
	}
	
	public void SetState(int state){
		
		m_StateMachine.SetState(state);
	}

}
