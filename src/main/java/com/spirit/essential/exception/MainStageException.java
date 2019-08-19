package com.spirit.essential.exception;


public class MainStageException extends Exception {

    private ErrorType type;
    private String code;
    private String text;

    public MainStageException(String code, String text) {
        super(text);
        this.code = code;
        this.text = text;
    }
    public MainStageException(ErrorType type) {
        super(type.Text());
        this.type = type;
    }
    public MainStageException(String text) {
        super(text);
        this.text = text;
    }
    public ErrorType getResultType() {
        return type;
    }
    public String getCode() {
        return type != null ? type.Code() : code;
    }
    public String getText() {
        return type != null ? type.Text() : text;
    }
}
