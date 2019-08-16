
package com.spirit.essential.thrift.socketserver.rpc.minicore;



public class SL_RPC_MessageParser {

	private SL_RPC_InProtocolBuilder m_inProtocol = null;
	
	
	public SL_RPC_MessageParser(byte[] msg, int size){
	
		if(msg != null){
			
			m_inProtocol = new SL_RPC_InProtocolBuilder(msg, size);
		}
	}
	
	public boolean Message_Parser(){
			
		return m_inProtocol.Parser();
	}
	
	public short GetHeaderType(){
		
		return m_inProtocol.GetHeaderType();
	}
	
	public int GetSource(){
		
		return m_inProtocol.GetSource();
	}

	public int GetAttach1(){
		
		return m_inProtocol.GetAttach1();
	}
	
	public RpcCommonHead GetHead(){
		
		return m_inProtocol.GetHead();
	} 
}
