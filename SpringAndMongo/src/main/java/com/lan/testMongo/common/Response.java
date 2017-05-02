package com.lan.testMongo.common;

/**
 * Created by tianxianglan on 2017/5/1.
 */
public class Response {
    private Object object;

    public Response() {
    }

    public Response(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
