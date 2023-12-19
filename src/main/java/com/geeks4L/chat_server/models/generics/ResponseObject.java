package com.geeks4L.chat_server.models.generics;

import com.geeks4L.chat_server.models.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class ResponseObject<T> {
    protected Status status;
    protected String message;
    protected T data;

    public ResponseObject(Status status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(){
        this.status = Status.NOT_FOUND;
        this.message = "Something went wrong";
        this.data = null;
    }
}
