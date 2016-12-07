package com.example.skuo.yuezhan.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 16-12-2.
 */
public class BaseEntity<E> implements Serializable {
    private int StatusCode;
    private String ErrorMsg;
    private E Data;

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

    public E getData() {
        return Data;
    }

    public void setData(E data) {
        this.Data = data;
    }
}
