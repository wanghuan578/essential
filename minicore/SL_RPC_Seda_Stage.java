

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_Seda_Stage {

	private static SL_RPC_Seda_Stage m_Instance = null;
	
	private static SL_RPC_Seda_StageThread m_SedaThread = null;
	
	public static synchronized SL_RPC_Seda_Stage Instance()
	{
		if(null == m_Instance)
		{
			m_Instance = new SL_RPC_Seda_Stage();
		}
		
		return m_Instance;
	}
	
	private SL_RPC_Seda_Stage(){
		
	}
	
	public void Open(){
		
		if(null == m_SedaThread){
			
			m_SedaThread = new SL_RPC_Seda_StageThread();
			
			m_SedaThread.Init();
		}
	}
	
	public void PushEvent(SL_RPC_Seda_Event event){
		
		SL_RPC_Seda_StageThread.PushEvent(event);
	}
	
	public void Destroy(){
		
		if(null != m_SedaThread){
		
			m_SedaThread.Destroy();
			
			m_SedaThread = null;
		}
	}
	
	public void Queue_Clear(){
		
		if(null != m_SedaThread){
		
			m_SedaThread.Clear();
		}
	}
	
	
	
}
