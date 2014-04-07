package com.macs.starter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Basic Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponse<T> {
    private String version = "1.0";
    private T content;
    private ErrorCode error;

    public BasicResponse(T content) {
        this.content = content;
    }

    public BasicResponse(ErrorCode error, T content) {
        this.error = error;
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

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }

    public String toJSON() {
        try {
            return new ObjectMapper().writer().writeValueAsString(this);
        } catch (Exception e) {
            return "INTERNAL SERVER EXCEPTION";
        }
    }
}
