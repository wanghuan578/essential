package com.spirit.essential.thrift.socketserver.rpc.minicore;


public class SL_RPC_UpdateServerRecvThread {

private static final int RCV_BUFFER_SIZE = (8 * 1024);
	
	private boolean m_IsSelectRunning = false;
	
	private SL_RPC_Socket_CommonAPI m_SocketCommAPI = null;
	
	private Thread m_RecvThread = null;
	
	public SL_RPC_UpdateServerRecvThread(SL_RPC_Socket_CommonAPI imp) {
		
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
		
		System.out.println("SL_RPC_UpdateRecvThread - Destorying");
		
		SetRunnningStatus(false);
		
		GetSocketCommAPI().Destroy();
	}

	private void SetRunnningStatus(boolean b) {
		
		m_IsSelectRunning = b;
	}
	
	private boolean GetRunnningStatus() {
		
		return m_IsSelectRunning;
	}
	
	public class ReceiveThread extends Thread{
			
		public ReceiveThread() {
			
		}
		
		private void Select() throws InterruptedException {

			RpcByteBuffer receive_buffer = new RpcByteBuffer(RCV_BUFFER_SIZE);
			
			switch(m_SocketCommAPI.Recv_N(receive_buffer, 0, 4)){
			
			case SL_RPC_ErrorCode.NETWORK_DISCONNECT:
			case SL_RPC_ErrorCode.SOCKET_EXCEPTION:{
				
				System.out.println("SL_RPC_UpdateRecvThread - 00000000 Select NetWork Disconnect");
				
				SetRunnningStatus(false);
			}
				break;

				
			default:{
				
				int packet_len = receive_buffer.ReadI32(0);
				
				if((packet_len < (RCV_BUFFER_SIZE - 4)) && (packet_len > 0)){
					
					int ret = m_SocketCommAPI.Recv_N(receive_buffer, 4, (packet_len - 4));
					
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
						
						receive_buffer.WriteBufferBegin(ret + 4);
						
						DispatchMessage(receive_buffer);
					}
						break;
					}
				}
				else
				{
					System.out.println("SL_RPC_UpdateRecvThread - Received Data Out Of Range Length: " + packet_len);
					
					SetRunnningStatus(false);
				}
			}
				break;
			}
			
		}
		
		public void run() {	

			while(true){
				
				if(!GetRunnningStatus()){

					System.out.println("SL_RPC_UpdateRecvThread - Thread Over");

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
	
	private void DispatchMessage(RpcByteBuffer buff){
			
		SL_RPC_UpdateServerHandler handler = new SL_RPC_UpdateServerHandler(buff);
	
		handler.handle_message();
	}
}
