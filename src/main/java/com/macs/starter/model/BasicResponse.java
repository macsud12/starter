package com.macs.starter.model;

/**
 * Created by Maksim_Alipov.
 */
public class BasicResponse<T> {
    private String version = "1.0";
    private T content;

    public BasicResponse(T content) {
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
