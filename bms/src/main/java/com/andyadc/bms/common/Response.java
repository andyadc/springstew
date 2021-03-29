package com.andyadc.bms.common;

import java.time.LocalDateTime;

public class Response<T> {

    private Integer status;
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    public Response() {
        this.timestamp = LocalDateTime.now();
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
