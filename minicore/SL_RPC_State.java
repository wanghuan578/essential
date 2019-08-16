

package com.spirit.essential.thrift.socketserver.rpc.minicore;

public class SL_RPC_State {

	public static final int SL_RPC_SOCKETSTATE_NONE 							= 0;
	
	public static final int SL_RPC_SOCKETSTATE_LOGINSERVER_CONNECTTING 			= 1;
	public static final int SL_RPC_SOCKETSTATE_LOGINSERVER_CONNECTED 			= 2;
	public static final int SL_RPC_SOCKETSTATE_LOGINSERVER_LOGIN 				= 3;
	public static final int SL_RPC_SOCKETSTATE_LOGINSERVER_LOGIN_SUCCESSED 		= 4;
	
	public static final int SL_RPC_SOCKETSTATE_ROOMGATESERVER_CONNECTTING 		= 5;
	public static final int SL_RPC_SOCKETSTATE_ROOMGATESERVER_CONNECTED 		= 6;
	public static final int SL_RPC_SOCKETSTATE_ROOMGATESERVER_LOGIN 			= 7;
	public static final int SL_RPC_SOCKETSTATE_ROOMGATESERVER_LOGIN_SUCCESSED 	= 8;
	
	public static final int SL_RPC_SOCKETSTATE_ROOMGATE_LOGIN_ERROR 			= 9;
	public static final int SL_RPC_SOCKETSTATE_CLOSE 							= 10;
}
