package com.example.demo1.entity;

/**
 * Created by acerpc on 2017/7/19.
 */
public class ResponseMessage {
    private String code;
    private String error;
    private Object result;

    public ResponseMessage(String code, String error, Object result) {
        this.code = code;
        this.error = error;
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public Object getResult() {
        return result;
    }
}
