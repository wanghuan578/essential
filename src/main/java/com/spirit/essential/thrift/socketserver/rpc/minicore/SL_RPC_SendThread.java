

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;



public class SL_RPC_SendThread {
	
	private boolean m_IsSendEnable = false;
	
	private SL_RPC_Socket_CommonAPI m_SocketComAPI = null;
	
	private Queue<RpcByteBuffer> m_SendQueue = null;
	
	private Thread m_SendThread = null;
	
	public SL_RPC_SendThread(SL_RPC_Socket_CommonAPI imp){
		
		SetRunnningStatus(true);
		
		m_SendQueue = new LinkedList<RpcByteBuffer>();
		
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
		
		System.out.println("SL_RPC_SendThread - Destory");
		
		SetRunnningStatus(false);
		
		GetSocketCommAPI().Destroy();
	}
	
	public boolean IsRunning(){
		
		return m_IsSendEnable;
	}
	
	private Queue<RpcByteBuffer> GetSendQueue(){
		
		return m_SendQueue;
	}
	
	public synchronized void PutMessageQueue(RpcByteBuffer buff){
		
		if(null != buff){
		
			GetSendQueue().add(buff);
		}
	}
	
	public synchronized void PutMessageQueueEx(RpcByteBuffer obj){
		
		byte[] temp = null;

		RpcByteBuffer body_buff = null;
		
		try 
		{
			temp = new SL_RPC_DataEncapsulater().encrypt(obj.GetBytes(), 0, obj.Length(), /*BusinessManager.Instance().GetLocalUserInfo().GetSessionKey()*/"123456");

			body_buff = new RpcByteBuffer(temp, temp.length);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		int  enlen = 16 - (obj.Length() % 16);
		
		if(16 == enlen){
			
			enlen = 0;
		}

		RpcByteBuffer buffer = new RpcByteBuffer(body_buff.Length() + 5);
		
		buffer.WriteI32(body_buff.Length() + 5);
		
		buffer.WriteByte((byte)enlen);
		
		buffer.StrCat(body_buff);
		
		GetSendQueue().add(buffer);
	}
	
	public class SendThread extends Thread{
		
		public SendThread(){
			
		}
		
		public void run(){
			
			while(true){
				
				if(!IsRunning()){
					
					System.out.println("SL_RPC_SendThread - Thread Over");
					
					break;
				}
					
				while(!GetSendQueue().isEmpty()){

					RpcByteBuffer msg = GetSendQueue().poll();
						
						if(null != msg){
								
							GetSocketCommAPI().Send_N(msg, 0);
						}
					}
				}

				try 
				{
					Thread.sleep(500);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	
}
