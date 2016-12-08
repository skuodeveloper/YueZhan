package com.example.skuo.yuezhan.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 16-12-2.
 */
public class BaseEntity<T> implements Serializable {
    private int StatusCode;
    private String ErrorMsg;
    private T Data;

    public int getCode() {
        return StatusCode;
    }

    public void setCode(int code) {
        this.StatusCode = code;
    }

    public String getMessage() {
        return ErrorMsg;
    }

    public void setMessage(String message) {
        this.ErrorMsg = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }
}
