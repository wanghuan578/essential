/************************************************************

Description: SL_Seda_StageThread.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.util.LinkedList;
import java.util.Queue;




public class SL_RPC_Seda_StageThread {
	
	private static Queue<SL_RPC_Seda_Event> m_EventQueue = null;

	private boolean m_IsRunning = false;
	
	private Thread m_SedaStageThread = null;
	
	public SL_RPC_Seda_StageThread(){
	
		m_IsRunning = true;
		
		m_EventQueue = new LinkedList<SL_RPC_Seda_Event>();
		
		m_SedaStageThread = new Thread(new StageThread());
	}
	
	public void Init() {
		
		m_SedaStageThread.start();
	}
	
	public synchronized static void PushEvent(SL_RPC_Seda_Event event){
		
		m_EventQueue.offer(event);
	}
	
	private Queue<SL_RPC_Seda_Event> GetEventQueue(){
		
		return m_EventQueue;
	}
	
	public void Clear(){
		
		m_EventQueue.clear();
	}
	
	private boolean IsRunning(){
		
		return m_IsRunning;
	}
	
	public void Destroy(){
		
		m_IsRunning = false;
	}
	
	public class StageThread implements Runnable{
		
		public StageThread(){
			
		}
		
		public void run(){
			
			while(true){
				
				if(!IsRunning()){
					
					System.out.println("SL_RPC_Seda_StageThread - StageThread Over");
					
					break;
				}
				
				while(!GetEventQueue().isEmpty()){
					
					SL_RPC_Seda_Event event = m_EventQueue.poll();
					
					if(null != event){
						
						int type = event.GetEventHead().GetType();
						
						switch(type){
						
						case SL_RPC_ErrorCode.SOCKET_EXCEPTION:
						case SL_RPC_ErrorCode.NETWORK_DISCONNECT:{
							
							//BusinessManager.Instance().NetWorkErrorNotify(type);
						}
							break;
							
						case SL_RPC_ErrorCode.CONNECT_TIMEOUT:{
							
							//BusinessManager.Instance().ConnectTimeOutNotify(type);
						}
							break;
							
						default:{
							
							SL_RPC_Seda_Handler seda = new SL_RPC_Seda_Handler(event);
							
							seda.handle_message();
						}
							break;
							
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


}
