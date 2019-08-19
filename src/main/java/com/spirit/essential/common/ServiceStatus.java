package com.spirit.essential.common;

public class ServiceStatus {

    private String path;
    private Long timestamp;
    private Short side;// 0 provider, 1 consumer

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
