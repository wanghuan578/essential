package com.spirit.essential.thrift.socketserver.rpc.minicore;


import java.util.LinkedList;
import java.util.Queue;



public class SL_RPC_UpdateServerSendThread {

private boolean m_IsSendEnable = false;
	
	private SL_RPC_Socket_CommonAPI m_SocketComAPI = null;
	
	private Queue<RpcByteBuffer> m_send_queue = null;
	
	private Thread m_SendThread = null;
	
	public SL_RPC_UpdateServerSendThread(SL_RPC_Socket_CommonAPI imp){
		
		SetRunnningStatus(true);
		
		m_send_queue = new LinkedList<RpcByteBuffer>();
		
		m_SocketComAPI = imp;
		
		m_SendThread = new Thread(new SendThread());
	}
	
	public SL_RPC_Socket_CommonAPI GetSocketCommAPI(){
		
		return m_SocketComAPI;
	}

	public void Listern() {
	
		m_SendThread.start();
	}
	
	private void SetRunnningStatus(boolean b){
		
		m_IsSendEnable = b;
	}
	
	public void Destory(){
		
		System.out.println("SL_RPC_UpdateSendThread - Destorying");
		
		SetRunnningStatus(false);
		
		GetSocketCommAPI().Destroy();
	}
	
	public boolean IsThreadEnable(){
		
		return m_IsSendEnable;
	}
	
	public synchronized void PutMessageQueue(RpcByteBuffer buff){
		
		if(null != buff){
		
			m_send_queue.add(buff);
		}
	}
	
	public class SendThread extends Thread{
		
		public SendThread(){
			
		}
		
		public void run(){
			
			while(true){
				
				if(!IsThreadEnable()){
					
					System.out.println("SL_RPC_UpdateSendThread - RoomGateServer SendThread Over");
					
					break;
				}
					
				if(!m_send_queue.isEmpty()){
					
					Send();
				}
				else
				{
					try 
					{
						Thread.sleep(100);
						
						continue;
					} 
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private boolean Send(){
		
		boolean ret = false;

		RpcByteBuffer buff = m_send_queue.poll();
		
		if(null != buff){
			
			System.out.println("SL_RPC_UpdateSendThread - Send Length: " + buff.Length());

			GetSocketCommAPI().Send_N(buff, 0);
			
			ret = true;
		}

		return ret;
	}
	
}
