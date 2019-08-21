package com.spirit.essential.exception;


public enum ErrorType {

    SERIALIZE_EXCEPTION("0001", "序列化失败"),
    DESERIALIZE_EXCEPTION("0002", "反序列化失败"),
    DUPLICATED_REGISTER_EXCEPTION("0003", "重复注册监听"),
    SERVICE_LIST_EMPTY_EXCEPTION("0004", "服务列表为空"),


    UNEXPECTED_EXCEPTION("1000", "未知异常"),



    ;

    private String code;
    private String text;

    ErrorType(String code, String name) {
        this.code = code;
        this.text = name;
    }

    public String Code() {
        return code;
    }

    public ErrorType SetCode(String code) {
        this.code = code;
        return this;
    }
    public String Text() {
        return text;
    }
    public ErrorType SetText(String text) {
        this.text = text;
        return this;
    }
}
