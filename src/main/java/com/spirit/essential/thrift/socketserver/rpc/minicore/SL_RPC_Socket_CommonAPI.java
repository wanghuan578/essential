package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SL_RPC_Socket_CommonAPI {

	private int m_ServerType = SL_RPC_ServerType.SL_RPC_SERVER_TYPE_NONE;
	
	private Socket m_SocketClient = null;
	
	public SL_RPC_Socket_CommonAPI(int server_type){
		
		SetServerType(server_type);
	}
	
	public void SetServerType(int server_type){
		
		m_ServerType = server_type;
	}
	
	public int GetServerType(){
		
		return m_ServerType;
	}
	
	public Socket GetClient(){
		
		return m_SocketClient;
	}
	
	public Socket Connect(String addr, int port){
			
		if((null != addr) && (0 != port)){
			
			m_SocketClient = new Socket();
			
			SocketAddress isa = new InetSocketAddress(addr, port);
			
			try 
			{
				m_SocketClient.connect(isa, 10000);
				
				System.out.println("SL_RPC_Socket_CommonAPI - " + addr + ": " + port + " Has Been Connected");
			}
			catch (IOException e) 
			{
				System.out.println("SL_RPC_Socket_CommonAPI - ConnectException");
				
				SL_RPC_Seda_Stage.Instance().PushEvent(new SL_RPC_Seda_Event(null, new RpcCommonHead(SL_RPC_ErrorCode.CONNECT_TIMEOUT)));
			}
		}
		else
		{
			System.out.println("SL_RPC_Socket_CommonAPI - Connect IP Invalid");
		}

		return m_SocketClient;
	}
	
	public int Recv_N(RpcByteBuffer buff, int offset, int len){
		
		int received_offset = offset;
		
		int ret = 0;
		
		int left = len;
		
		while(received_offset < len){
			
			if(null != GetClient()){
				
				try 
				{
					InputStream in = GetClient().getInputStream();
					
					if(null != in){
						
						ret = in.read(buff.GetBytes(), received_offset, left);
						
					    if (-1 != ret){
					    	
					    	received_offset += ret;
					    	
					    	left -= ret;
					    }
					    else if(SL_RPC_ErrorCode.NETWORK_DISCONNECT == ret)
					    {
					    	
					    	if(received_offset > 0){
					    		
					    		System.out.println("SL_RPC_Socket_CommonAPI - Recv_N Finished");
					    		
					    		return received_offset;
					    	}
					    	else
					    	{
					    		System.out.println("SL_RPC_Socket_CommonAPI - Recv_N NetWork Disconnect");
					    		
					    		return SL_RPC_ErrorCode.NETWORK_DISCONNECT;
					    	}
					    	
					    }
					}
				} 
				catch (IOException e) 
				{
					System.out.println("SL_RPC_Socket_CommonAPI - Recv_N Socket IOException");
					
					e.printStackTrace();
					
					return SL_RPC_ErrorCode.SOCKET_EXCEPTION;
				}
			}
			else
			{
				System.out.println("SL_RPC_Socket_CommonAPI - Recv_N Socket Close");
				
				return SL_RPC_ErrorCode.SOCKET_COLSE;
			}
		}
		
		System.out.println("SL_RPC_Socket_CommonAPI - Recv_N: " + received_offset);
		
		return received_offset;
	}
	
	public int Send_N(RpcByteBuffer buff, int offset){
		
		if(null != GetClient()){
			
			int len = buff.Length();
			
			try 
			{
				System.out.println("SL_RPC_Socket_CommonAPI - Send_N: " + len);
				
				GetClient().getOutputStream().write(buff.GetBytes(), offset, len);
				
				GetClient().getOutputStream().flush();
			} 
			catch (IOException e) 
			{
				System.out.println("SL_RPC_Socket_CommonAPI - IOException");
			}
		}
		else
		{
			System.out.println("SL_RPC_Socket_CommonAPI - Send_N Socket Close");
		}
		
		return 0;
	}
	
	public void Destroy(){
		
		System.out.println("SL_RPC_Socket_CommonAPI - Destroy");
		
		if(null != GetClient()){
			
			if(GetClient().isConnected()){
				
				try 
				{
					GetClient().close();
					
					m_SocketClient = null;
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
