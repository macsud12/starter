package com.macs.starter.model;

/**
 * Created by Maksim_Alipov on 1/31/14.
 */
public class BasicResponse {
    private String version = "1.0";
    private Object content;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
