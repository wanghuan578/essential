

package com.spirit.essential.thrift.socketserver.rpc.minicore;



public class SL_RPC_InProtocolBuilder {
	
	private RpcByteBuffer m_ProtocolObject = null;
	
	private RpcCommonHead m_comm_head = null;
	
	public SL_RPC_InProtocolBuilder(byte[] string, int size){
		
		m_ProtocolObject = new RpcByteBuffer(string, size);
		
		m_comm_head = new RpcCommonHead();
		
	}
	
	public boolean Parser(){
		
		boolean ret = false;
		
		ret = Comm_Head_Parser();
		
		return ret;
	}
	
	private boolean CommHeadAnalyse(){
	
//		m_comm_head.SetLength(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetFlag(m_ProtocolObject.ReadI16());
//
//		m_comm_head.SetType(m_ProtocolObject.ReadI16());
//
//		m_comm_head.SetSequence(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetSource(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetDestination(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetCheckSum(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetAttach1(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetAttach2(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetAttach3(m_ProtocolObject.ReadI32());
//
//		m_comm_head.SetAttach4(m_ProtocolObject.ReadI32());
		
		return true;
	}
	
	private boolean Comm_Head_Parser(){
		
		return CommHeadAnalyse();
	}
	
	public short GetHeaderType(){
		
		return m_comm_head.GetType();
	}
	
	public int GetSource(){
		
		return m_comm_head.GetSource();
	}

	public int GetAttach1(){
		
		return m_comm_head.GetAttach1();
	}
	
	public RpcCommonHead GetHead(){
		
		return m_comm_head;
	}
}
