

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_CommHead {

private static final int XX_RPC_COMM_HEAD_SIZE = 40;
	
	private int    	m_length;            
	private short  	m_flag;              
	private short  	m_type;               
	private int    	m_sequence;          
	private int    	m_source;            
	private int    	m_destination;       
	private int     m_checksum;         
	private int     m_attach_id1;       
	private int     m_attach_id2;     
	private int     m_attach_id3;        
	private int     m_attach_id4;
	
	public SL_RPC_CommHead(){
		
		m_length = 0;            
		m_flag = 0;              
		m_type = 0;              
		m_sequence = 0;          
		m_source = 0;            
		m_destination = 0;      
		m_checksum = 0;         
		m_attach_id1 = 0;      
		m_attach_id2 = 0;   
		m_attach_id3 = 0;        
		m_attach_id4 = 0;
	}

	public SL_RPC_CommHead(int type){
		
		m_length = 0;            
		m_flag = 0;              
		m_type = (short)type;              
		m_sequence = 0;          
		m_source = 0;            
		m_destination = 0;      
		m_checksum = 0;         
		m_attach_id1 = 0;      
		m_attach_id2 = 0;   
		m_attach_id3 = 0;        
		m_attach_id4 = 0;
	}
	
	public static int Size(){
		
		return XX_RPC_COMM_HEAD_SIZE;
	}
	
	public int GetLength(){
		
		return m_length;
	}

	public short GetFlag(){
		
		return m_flag;
	}
	
	public short GetType(){
		
		return m_type;
	}
	
	public int GetSequence(){
		
		return m_sequence;
	}
	
	public int GetSource(){
		
		return m_source;
	}
	
	public int GetDestination(){
		
		return m_destination;
	}
	
	public int GetCheckSum(){
		
		return m_checksum;
	}
	
	public int GetAttach1(){
		
		return m_attach_id1;
	}
	
	public int GetAttach2(){
		
		return m_attach_id2;
	}
	
	public int GetAttach3(){
		
		return m_attach_id3;
	}
	
	public int GetAttach4(){
		
		return m_attach_id4;
	}
	
	public void SetLength(int len){
		
		m_length = len;
	}

	public void SetFlag(short flag){
		
		m_flag = flag;
	}
	
	public void SetType(int type){
		
		m_type = (short)type;
	}
	
	public void SetSequence(int seq){
		
		m_sequence = seq;
	}
	
	public void SetSource(int source){
		
		m_source = source;
	}
	
	public void SetDestination(int des){
		
		m_destination = des;
	}
	
	public void SetCheckSum(int checksum){
		
		m_checksum = checksum;
	}
	
	public void SetAttach1(int att1){
		
		m_attach_id1 = att1;
	}
	
	public void SetAttach2(int att2){
		
		m_attach_id2 = att2;
	}
	
	public void SetAttach3(int att3){
	
		m_attach_id3 = att3;
	}
	
	public void SetAttach4(int att4){
	
		m_attach_id4 = att4;
	}
}
