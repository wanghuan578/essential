package com.spirit.essential.thrift.socketserver.rpc.minicore;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;


public class SL_RPC_ByteBuffer {
	
	private int m_BufferSize = 0;
	
	private byte[] m_buffer = null;
	
	private int m_WritebufferEnd = 0;
	
	private int m_ReadbufferEnd = 0;
	
	private String m_StringBuff = null;
	
	public SL_RPC_ByteBuffer(int len){
		
		//System.out.println("SL_RPC_ByteBuffer - Create Buffer Length: " + len);
		
		m_BufferSize = len;
		
		m_WritebufferEnd = 0;
		
		m_ReadbufferEnd = 0;
		
		m_buffer = new byte[m_BufferSize];
	}
	
	public SL_RPC_ByteBuffer(byte[] bytes, int len){
		
		if(null != bytes)
		{
			m_WritebufferEnd = len;
			
			m_ReadbufferEnd = 0;
			
			m_buffer = new byte[m_WritebufferEnd];
			
			for(int index = 0; index < m_WritebufferEnd; index++){
				
				m_buffer[index] = bytes[index];
			}
		}
	}
	
	public SL_RPC_ByteBuffer(SL_RPC_ByteBuffer buff, int offset){
		
		m_BufferSize = buff.Length() - offset;
		
		m_buffer = new byte[m_BufferSize];
		
		System.arraycopy(buff.GetBytes(), offset, m_buffer, 0, m_BufferSize);
	}
	
	public SL_RPC_ByteBuffer(SL_RPC_ByteBuffer buff){
		
		m_WritebufferEnd = buff.Length();
		
		m_buffer = new byte[m_WritebufferEnd];
		
		m_buffer = buff.GetBytes();
	}
	
	public SL_RPC_ByteBuffer(String source){
		
		m_StringBuff = source;
	}
	
	public String Append(String source){
		
		String tmp_buff = null;
		
		tmp_buff = m_StringBuff + source;
		
		return (m_StringBuff = tmp_buff);
	}
	
	public String GetString(){
		
		return m_StringBuff;
	}
	
	public int Size(){
		
		return m_BufferSize;
	}
	
	public int Length(){
		
		return m_WritebufferEnd;
	}

	public byte[] GetBytes(){
	
		return m_buffer;
	}
	
	public void WriteBufferBegin(int pos){
		
		m_WritebufferEnd = pos;
	}
	
	public void ReadBufferBegin(int pos){
		
		m_ReadbufferEnd = pos;
	}
	
	public void SkipWriteBuffer(int offset){
		
		m_WritebufferEnd += offset;
	}
	
	public void SkipReadBuffer(int offset){
		
		m_ReadbufferEnd += offset;
	}
	
	public void SetBuffer(byte[] str){
		
		m_buffer = str;
	}
	
	//Write Data
	public void WriteByte(byte b){
		
		m_buffer[m_WritebufferEnd++] = b;
	}
	
	public void WriteBool(boolean b){
		
		WriteByte(b ? (byte)1 : (byte)0);
	}
	
	public boolean WriteI16(short i16){
		
		try
		{
			m_buffer[m_WritebufferEnd++] = (byte) (i16>>8 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i16 & 0xff);
		}
		catch(Exception e)
		{
			System.out.println("SL_RPC_ByteBuffer - Array bounds " + e.toString());
			
			return false;
		}
		
		return true;
	}

	public boolean WriteI32(int i32){
		
		try
		{
			m_buffer[m_WritebufferEnd++] = (byte) (i32>>24 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i32>>16 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i32>>8 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i32 & 0xff);
		} 
		catch (Exception e) 
		{
			System.out.println("SL_RPC_ByteBuffer - Array bounds " + e.toString());
			
			return false;
		}
		
		return true;
	}
	
	public boolean WriteI64(long i64){
		
		try
		{
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>56 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>48 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>40 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>32 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>24 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>16 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64>>8 & 0xff);
			m_buffer[m_WritebufferEnd++] = (byte) (i64 & 0xff);

		} 
		catch (Exception e) 
		{
			System.out.println("SL_RPC_ByteBuffer - Array bounds " + e.toString());
			
			return false;
		}
		
		return true;
	}
	
	public boolean WriteDouble(double d64){
		
		WriteI64(Double.doubleToLongBits(d64));
		
		return true;
	}
	
	public boolean WriteString(String str){
		
		byte[] dat = null;
		
		try 
		{
			dat = str.getBytes("UTF-8");
			
			if(null == dat){
				
				WriteI32(0);
			}
			else
			{
				WriteI32(dat.length);
			    
				System.arraycopy(dat, 0, m_buffer, m_WritebufferEnd,  dat.length);
				
				m_WritebufferEnd += dat.length;
			}
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
	    return true;
	}
	
	public boolean WriteBinary(ByteBuffer buff){
		
		int length = buff.limit() - buff.position() - buff.arrayOffset();
		  
		WriteI32(length);
				  
		System.arraycopy(buff.array(), (buff.position() + buff.arrayOffset()), m_buffer, m_WritebufferEnd, length);
		
		m_WritebufferEnd += length;
		
		return true;
	}
	    
	public boolean WriteBinary(byte[] data, int length){
		
		try
		{
			for(int index = 0; index < length; index++)
			{
				m_buffer[m_WritebufferEnd++] = data[index];
			}
			
			m_WritebufferEnd += length;
		}
		catch(Exception e)
		{
			System.out.println("SL_RPC_ByteBuffer - Array bounds " + e.toString());
			
			return false;
		}
		
		return true;
	}
	
	public byte ReadByte(){
		
		byte b = (byte)m_buffer[m_ReadbufferEnd++];
		
		return b;
	}

	public boolean ReadBool(){
		
		return (boolean)(1 == ReadByte());
	}
	
	public short ReadI16(){
		
		short result = (short)(((m_buffer[m_ReadbufferEnd++] & 0xff) << 8) |
	       			 			(m_buffer[m_ReadbufferEnd++] & 0xff));
	       				
	    return result;
	}
	
	public int ReadI32(){
		
		 int result  = 	((m_buffer[m_ReadbufferEnd++] & 0xff) << 24) |
			      		((m_buffer[m_ReadbufferEnd++] & 0xff) << 16) |
			      		((m_buffer[m_ReadbufferEnd++] & 0xff) <<  8) |
			      		(m_buffer[m_ReadbufferEnd++] & 0xff);
				  
		return result;
	}
	
	public int ReadI32(int position){
		
		 int result  = 	((m_buffer[position + 0] & 0xff) << 24) |
			      		((m_buffer[position + 1] & 0xff) << 16) |
			      		((m_buffer[position + 2] & 0xff) <<  8) |
			      		 (m_buffer[position + 3] & 0xff);
				  
		return result;
	}
	
	public long ReadI64(){
		
		 long result  = ((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 56) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 48) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 40) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 32) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 24) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) << 16) |
			      		((long)(m_buffer[m_ReadbufferEnd++] & 0xff) <<  8) |
			      		((long)m_buffer[m_ReadbufferEnd++] & 0xff);
				  
		return result;
	}
	
	public String ReadString() throws TException
	{
		int size = ReadI32();
		
		try 
		{			  
			byte[] buf = new byte[size];
			  
			System.arraycopy(m_buffer, m_ReadbufferEnd, buf, 0, size);
			  
			m_ReadbufferEnd += size;
			  
			return new String(buf, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		  
		return null;
	}
	
	public void StrCat(SL_RPC_ByteBuffer in){
		
		System.arraycopy(in.GetBytes(), 0, m_buffer, m_WritebufferEnd,  in.Length());
		
		m_WritebufferEnd += in.Length();
	}
	
}
