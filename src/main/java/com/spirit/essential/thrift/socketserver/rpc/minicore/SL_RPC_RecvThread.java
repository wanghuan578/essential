/************************************************************

Description: SL_RPC_RecvThread.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;


public class SL_RPC_RecvThread {
	
	private boolean m_IsSelectRunning = false;
	
	private SL_RPC_Socket_CommonAPI m_SocketCommAPI = null;
	
	private Thread m_RecvThread = null;
	
	public SL_RPC_RecvThread(SL_RPC_Socket_CommonAPI imp) {
		
		SetRunnningStatus(true);
		
		m_SocketCommAPI = imp;
		
		m_RecvThread = new Thread(new ReceiveThread());
	}
	
	public SL_RPC_Socket_CommonAPI GetSocketCommAPI(){
		
		return m_SocketCommAPI;
	}
	
	public void Listern() {
		
		m_RecvThread.start();
	}
	
	public void Destory(){
		
		System.out.println("SL_RPC_RecvThread - Destory");
		
		SetRunnningStatus(false);
		
		GetSocketCommAPI().Destroy();
	}

	private void SetRunnningStatus(boolean b) {
		
		m_IsSelectRunning = b;
	}
	
	private boolean IsRunning() {
		
		return m_IsSelectRunning;
	}
	
	public class ReceiveThread extends Thread{
			
		public ReceiveThread() {
			
		}
		
		private void Select() throws InterruptedException {
			
			SL_RPC_ByteBuffer len_buffer = new SL_RPC_ByteBuffer(4);
			
			switch(m_SocketCommAPI.Recv_N(len_buffer, 0, 4)){
			
			case SL_RPC_ErrorCode.NETWORK_DISCONNECT:{
				
				System.out.println("SL_RPC_RecvThread - 00000000 Select NetWork Disconnect");
				
				SetRunnningStatus(false);
			}
				break;
				
			case SL_RPC_ErrorCode.SOCKET_EXCEPTION:{
				
				System.out.println("SL_RPC_RecvThread - 00000000 Select Socket Exception");
				
				SetRunnningStatus(false);
			}
				break;

				
			default:{
				
				int buff_len = len_buffer.ReadI32(0);
				
				SL_RPC_ByteBuffer receive_buffer = new SL_RPC_ByteBuffer(buff_len);
				
				receive_buffer.WriteI32(buff_len);
				
				if(0 < buff_len){
					
					int ret = m_SocketCommAPI.Recv_N(receive_buffer, 4, (buff_len - 4));
					
					switch(ret){
					
					case SL_RPC_ErrorCode.NETWORK_DISCONNECT:{
						
						System.out.println("SL_RPC_RecvThread - 11111111 Select NetWork Disconnect");
						
						SetRunnningStatus(false);
					}
						break;
						
					case SL_RPC_ErrorCode.SOCKET_EXCEPTION:{
						
						System.out.println("SL_RPC_RecvThread - 11111111 Select Socket Exception");
						
						SetRunnningStatus(false);
					}
						break;
					
					default:{
								
						DispatchMessage(receive_buffer, buff_len);
					}
						break;
					}
				}
				else
				{
					System.out.println("SL_RPC_RecvThread - Fatal Error Received Data Out Of Range Length: " + buff_len);
					
					//SL_RPC_Seda_Stage.Instance().PushEvent(new SL_RPC_Seda_Event(null, new SL_RPC_CommHead(SL_RPC_ErrorCode.NETWORK_DISCONNECT)));

					SetRunnningStatus(false);
				}
			}
				break;
			}
			
		}
		
		public void run() 
		{	
			while(true) 
			{
				if(!IsRunning()){

					System.out.println("SL_RPC_RecvThread - Thread Over");
					
					break;
				}
				
				try 
				{
					Select();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}

	}
	
	private void DispatchMessage(SL_RPC_ByteBuffer buff, int len){
		
		buff.WriteBufferBegin(len);
		
		SL_RPC_MainStageHandler main_stage = new SL_RPC_MainStageHandler(buff);
		
		main_stage.Initialize();
		
		main_stage.handle_message();
	}
}
