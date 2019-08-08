package com.spirit.essential.common;

import lombok.Data;

@Data
public class Event {
    public Event(int type, Object body) {
        this.type = type;
        this.body = body;
    }
    private int type;
    private Object body;
}
