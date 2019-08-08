package com.spirit.essential.common;

import com.spirit.essential.thrift.socketserver.rpc.minicore.RpcCommonHead;
import lombok.Data;

@Data
public class Event {
    public Event(RpcCommonHead head, Object body) {
        this.head = head;
        this.body = body;
    }
    private RpcCommonHead head;
    private Object body;
}
