

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.util.TimerTask;



public class SL_RPC_KeepALiveTask extends TimerTask {
	
	private long m_Index = 0;
	
	public SL_RPC_KeepALiveTask(){
		
	}
	
	@Override
	public void run() {
		
		System.out.println("SL_RPC_KeepALiveTask - SRM_KeepLiveRequest: " + m_Index++);
		
		//new SRM_KeepLiveRequest();
	}

}
