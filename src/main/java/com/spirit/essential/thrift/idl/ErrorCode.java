package com.spirit.essential.thrift.idl;
/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ErrorCode implements TEnum {
  OK(0),
  TICKET_INVALID(100),
  LOGNAME_EXIST(101),
  LOGNAME_NOT_EXIST(102),
  PASSWORD_ERROR(103),
  USERID_INVALID(104),
  PASS_EXPIRYDATE(105),
  ACCOUNT_LOCKED(106),
  INVALID_ACCOUNT(107),
  CLIENT_VERSION_INVALID(108),
  ACCOUNT_ACCTIVED(109),
  VERIFYCODE_VERIFIED(110),
  USER_IN_BLACKLIST(111),
  LOGIN_CONNECT_ERROR(112),
  ROOMGATE_CONNECT_ERROR(113),
  NICKNAME_DUPLICATE(114),
  ROOM_UNKNOWN(115),
  ROOM_STATUS_NO_STARTUP(116),
  ROOM_STATUS_CLOSE(117),
  ROOM_STATUS_LOCKED(118),
  ROOM_USER_TICKET_INVALID(119),
  ROOM_USER_ID_INVALID(120),
  ROOM_USER_NO_AUTHORITY(121),
  ROOM_USER_MONEY_LESS(122),
  ROOM_USER_KICK_OUT(123),
  ROOM_USER_OTHER_LOCAL_LOGIN(124),
  ROOM_USER_COUNT_MAX(125),
  ROOM_NEED_PASSWORD(126),
  ROOM_PASSORD_ERROR(127),
  ROOM_TO_USERID_INVALID(128),
  REQUEST_ERROR(129),
  REQUEST_TIMEOUT(130),
  DB_CONNECT_ERROR(131),
  DB_OPERATION_EXCEPTION(132),
  SYSTEM_BUSY(133),
  UNKOWN_ERROR(134);

  private final int value;

  private ErrorCode(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ErrorCode findByValue(int value) { 
    switch (value) {
      case 0:
        return OK;
      case 100:
        return TICKET_INVALID;
      case 101:
        return LOGNAME_EXIST;
      case 102:
        return LOGNAME_NOT_EXIST;
      case 103:
        return PASSWORD_ERROR;
      case 104:
        return USERID_INVALID;
      case 105:
        return PASS_EXPIRYDATE;
      case 106:
        return ACCOUNT_LOCKED;
      case 107:
        return INVALID_ACCOUNT;
      case 108:
        return CLIENT_VERSION_INVALID;
      case 109:
        return ACCOUNT_ACCTIVED;
      case 110:
        return VERIFYCODE_VERIFIED;
      case 111:
        return USER_IN_BLACKLIST;
      case 112:
        return LOGIN_CONNECT_ERROR;
      case 113:
        return ROOMGATE_CONNECT_ERROR;
      case 114:
        return NICKNAME_DUPLICATE;
      case 115:
        return ROOM_UNKNOWN;
      case 116:
        return ROOM_STATUS_NO_STARTUP;
      case 117:
        return ROOM_STATUS_CLOSE;
      case 118:
        return ROOM_STATUS_LOCKED;
      case 119:
        return ROOM_USER_TICKET_INVALID;
      case 120:
        return ROOM_USER_ID_INVALID;
      case 121:
        return ROOM_USER_NO_AUTHORITY;
      case 122:
        return ROOM_USER_MONEY_LESS;
      case 123:
        return ROOM_USER_KICK_OUT;
      case 124:
        return ROOM_USER_OTHER_LOCAL_LOGIN;
      case 125:
        return ROOM_USER_COUNT_MAX;
      case 126:
        return ROOM_NEED_PASSWORD;
      case 127:
        return ROOM_PASSORD_ERROR;
      case 128:
        return ROOM_TO_USERID_INVALID;
      case 129:
        return REQUEST_ERROR;
      case 130:
        return REQUEST_TIMEOUT;
      case 131:
        return DB_CONNECT_ERROR;
      case 132:
        return DB_OPERATION_EXCEPTION;
      case 133:
        return SYSTEM_BUSY;
      case 134:
        return UNKOWN_ERROR;
      default:
        return null;
    }
  }
}
