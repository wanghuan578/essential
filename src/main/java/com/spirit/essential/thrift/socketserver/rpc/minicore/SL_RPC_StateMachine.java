/************************************************************

Description: SL_RPC_StateMachine.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_StateMachine {

	private int m_state = 0;
	
	public SL_RPC_StateMachine(){
		
		m_state = SL_RPC_State.SL_RPC_SOCKETSTATE_NONE;
	}
	
	public void SetState(int state){
		
		m_state = state;
	}
	
	public int GetState(){
		
		return m_state;
	}
}
