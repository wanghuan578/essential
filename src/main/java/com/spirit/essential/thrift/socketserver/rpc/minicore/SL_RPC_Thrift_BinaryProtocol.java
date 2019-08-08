

package com.spirit.essential.thrift.socketserver.rpc.minicore;


import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

public class SL_RPC_Thrift_BinaryProtocol extends TProtocol {
	
	private static final TStruct ANONYMOUS_STRUCT = new TStruct();
	
	private SL_RPC_ByteBuffer m_WriteBuffer = null;
	
	private SL_RPC_ByteBuffer m_ReadBuffer = null;
	
	public SL_RPC_Thrift_BinaryProtocol(){
		
		super(null);
	}
	
	public SL_RPC_Thrift_BinaryProtocol(SL_RPC_ByteBuffer out, int offset){
		
		super(null);
		
		m_WriteBuffer = out;
		
		m_WriteBuffer.SkipWriteBuffer(offset);
	}
	
	public SL_RPC_Thrift_BinaryProtocol(SL_RPC_ByteBuffer in, int offset, int size){
		
		super(null);
		
		m_ReadBuffer = new SL_RPC_ByteBuffer(in, offset);
	}


	@Override
	public void writeMessageBegin(TMessage message) throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeMessageEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStructBegin(TStruct struct) throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStructEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeFieldBegin(TField field) throws TException {
		  writeByte(field.type);
		  writeI16(field.id);
	}

	@Override
	public void writeFieldEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeFieldStop() throws TException {

		 writeByte(TType.STOP);
	}

	@Override
	public void writeMapBegin(TMap map) throws TException {

		writeByte(map.keyType);
		
		writeByte(map.valueType);
		
		writeI32(map.size);
	}

	@Override
	public void writeMapEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeListBegin(TList list) throws TException {

		 writeByte(list.elemType);
		 
		 writeI32(list.size);
	}

	@Override
	public void writeListEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeSetBegin(TSet set) throws TException {

		  writeByte(set.elemType);
		  
		  writeI32(set.size);
	}

	@Override
	public void writeSetEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeBool(boolean b) throws TException {

		m_WriteBuffer.WriteBool(b);
	}

	@Override
	public void writeByte(byte b) throws TException {

		m_WriteBuffer.WriteByte(b);
	}

	@Override
	public void writeI16(short i16) throws TException {
		
		m_WriteBuffer.WriteI16(i16);
	}

	@Override
	public void writeI32(int i32) throws TException {

		m_WriteBuffer.WriteI32(i32);
	}

	@Override
	public void writeI64(long i64) throws TException {

		m_WriteBuffer.WriteI64(i64);
	}

	@Override
	public void writeDouble(double dub) throws TException {

		m_WriteBuffer.WriteDouble(dub);
	}

	@Override
	public void writeString(String str) throws TException {

		m_WriteBuffer.WriteString(str);
	}

	@Override
	public void writeBinary(ByteBuffer buf) throws TException {

		m_WriteBuffer.WriteBinary(buf);
	}

	@Override
	public TMessage readMessageBegin() throws TException {
		 
		 return new TMessage(new String(m_ReadBuffer.GetBytes()), readByte(), readI32());
	}

	@Override
	public void readMessageEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TStruct readStructBegin() throws TException {

		return ANONYMOUS_STRUCT;
	}

	@Override
	public void readStructEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TField readFieldBegin() throws TException {

		byte type = readByte();
		
		short id = type == TType.STOP ? 0 : readI16();
		
		return new TField("", type, id);
	}

	@Override
	public void readFieldEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TMap readMapBegin() throws TException {

		return new TMap(readByte(), readByte(), readI32());
	}

	@Override
	public void readMapEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TList readListBegin() throws TException {

		return new TList(readByte(), readI32());
	}

	@Override
	public void readListEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TSet readSetBegin() throws TException {

		return new TSet(readByte(), readI32());
	}

	@Override
	public void readSetEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean readBool() throws TException {

		return m_ReadBuffer.ReadBool();
	}

	@Override
	public byte readByte() throws TException {

		return m_ReadBuffer.ReadByte();
	}

	@Override
	public short readI16() throws TException {

		return m_ReadBuffer.ReadI16();
	}

	@Override
	public int readI32() throws TException {

		return m_ReadBuffer.ReadI32();
	}

	@Override
	public long readI64() throws TException {

		return m_ReadBuffer.ReadI64();
	}

	@Override
	public double readDouble() throws TException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String readString() throws TException {

		return m_ReadBuffer.ReadString();
	}

	@Override
	public ByteBuffer readBinary() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
