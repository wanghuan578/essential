package com.spirit.essential.thrift.socketserver.rpc.minicore;

import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcCommonHead;
import lombok.Data;

@Data
public class EventPair {
    public EventPair(RpcCommonHead head, Object body) {
        this.head = head;
        this.body = body;
    }
    private RpcCommonHead head;
    private Object body;
}
