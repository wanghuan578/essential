/************************************************************

Description: SL_RPC_ProtocolFactory <TMessageBody>.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class SL_RPC_ProtocolFactory <TMessageBody extends TBase> {

	private SL_RPC_MessageParser m_cmdParser = null;
	
	//private SL_RPC_MessageBuilder<TMessageBody> m_CmdBuilder = null;

	private SL_RPC_CommHead head_;
	private TMessageBody body_;

	private SL_RPC_ByteBuffer byteBuff = null;

	private TProtocol tProtocol = null;

	public int serialize() throws TException {
		body_.write(tProtocol);
		return SerializeHead();
	}

	public SL_RPC_ProtocolFactory (TMessageBody body, SL_RPC_CommHead head, int buff_size, int offset) {

		//m_CmdBuilder = new SL_RPC_MessageBuilder<TMessageBody>(body, buff_size, offset);

		byteBuff = new SL_RPC_ByteBuffer(buff_size);

		tProtocol = new SL_RPC_Thrift_BinaryProtocol(byteBuff, offset);

		head_ = head;

		body_ = body;
	}

	public int SerializeHead(){

		int end = byteBuff.Length();

		byteBuff.WriteBufferBegin(0);
		byteBuff.WriteI32(end);
		byteBuff.WriteI16(head_.GetFlag());
		byteBuff.WriteI16(head_.GetType());
		byteBuff.WriteI32(head_.GetSequence());
		byteBuff.WriteI32(head_.GetSource());
		byteBuff.WriteI32(head_.GetDestination());
		byteBuff.WriteI32(head_.GetCheckSum());
		byteBuff.WriteI32(head_.GetAttach1());
		byteBuff.WriteI32(head_.GetAttach2());
		byteBuff.WriteI32(head_.GetAttach3());
		byteBuff.WriteI32(head_.GetAttach4());
		byteBuff.WriteBufferBegin(end);

		return end;
	}

	public SL_RPC_ByteBuffer getByteBuf() {
		return byteBuff;
	}

	public SL_RPC_ProtocolFactory(SL_RPC_ByteBuffer buff){
		
		m_cmdParser = new SL_RPC_MessageParser(buff.GetBytes(), buff.Length());
	}
	
//	public TMessageBody GetBody() {
//	    return m_CmdBuilder.GetBody();
//	}
//	public TProtocol GetProtocol() {
//        return m_CmdBuilder.GetTProtocol();
//    }
	
//	public SL_RPC_MessageBuilder<TMessageBody> GetHead() {
//
//	    return m_CmdBuilder;
//	}
	
	public SL_RPC_MessageParser GetParser(){

		if(null != m_cmdParser)
		{
			return m_cmdParser;
		}
		else
		{
			return null;
		}
	}
	

}
