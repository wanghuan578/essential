
/************************************************************

Description: SL_RPC_IdentityType.

Author: wanghuan. 2013-01-20.

Boxin Technology Corporated Corporation. All Rights Reserved.

*************************************************************/

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_IdentityType {

	public static final int TYPE_TEMP_USER_LOGIN 							= 0;	
	public static final int TYPE_REGISTERED_USER_LOGIN 						= 1;	
	public static final int TYPE_TEMP_TO_REGISTERED_USER_LOGIN 				= 2;
	public static final int TYPE_REGISTERED_TO_TEMP_USER_LOGIN 				= 3;
	public static final int TYPE_REGISTERED_TO_OTHERUSER_LOGIN 				= 4;
}
