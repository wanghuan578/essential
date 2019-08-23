package com.spirit.essential.common.exception;


public enum ExceptionCode {

    SUCCESS("0000", "OK"),
    SERIALIZE_EXCEPTION("0001", "序列化失败"),
    DESERIALIZE_EXCEPTION("0002", "反序列化失败"),
    DUPLICATED_REGISTER_EXCEPTION("0003", "重复注册监听"),
    SERVICE_LIST_EMPTY_EXCEPTION("0004", "服务列表为空"),
    NODE_SERVICE_DATA_EMPTY_EXCEPTION("0005", "节点服务数据为空"),


    UNEXPECTED_EXCEPTION("1000", "未知异常"),



    ;

    private String code;
    private String text;

    ExceptionCode(String code, String name) {
        this.code = code;
        this.text = name;
    }

    public String code() {
        return code;
    }

    public ExceptionCode setCode(String code) {
        this.code = code;
        return this;
    }
    public String text() {
        return text;
    }
    public ExceptionCode setText(String text) {
        this.text = text;
        return this;
    }
}
