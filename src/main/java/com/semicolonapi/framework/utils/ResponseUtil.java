package com.semicolonapi.framework.utils;


import com.semicolonapi.framework.models.ResponseDto;

import java.util.List;

public class ResponseUtil {

    public enum ResponseType {
        SUCCESS(200, "success"),
        EMPTY(204, "success but empty"),
        FAILURE(400, "failure"),
        UNAUTHORIZED(401, "you are not authorized to access"),
        FORBIDDEN(403, "you are forbidden to access");


        private int status;
        private String msg;

        ResponseType(int status, String msg){
            this.status = status;
            this.msg = msg;
        }

    }
    public static<T> ResponseDto.Data<T> returnSingle(T data){
        if(data == null || data == ""){
            return ResponseDto.createResponse(ResponseType.EMPTY.msg, ResponseType.EMPTY.status, null);
        }else {
            return ResponseDto.createResponse(ResponseType.SUCCESS.msg, ResponseType.SUCCESS.status, data);
        }
    }
    public static<T> ResponseDto.Data<List<T>> returnList(List<T> list){
        if(list.isEmpty()){
            return ResponseDto.createResponse(ResponseType.EMPTY.msg, ResponseType.EMPTY.status, null);
        }else {
            return ResponseDto.createResponse(ResponseType.SUCCESS.msg, ResponseType.SUCCESS.status, list);
        }
    }
    public static<T> ResponseDto.Data<T> returnResult(T data){
        if(data == null || data == ""){
            return ResponseDto.createResponse(ResponseType.EMPTY.msg, ResponseType.EMPTY.status, null);
        }else {
            return ResponseDto.createResponse(ResponseType.SUCCESS.msg, ResponseType.SUCCESS.status, data);
        }
    }
    public static ResponseDto.Signal returnSignal(int status, String msg){
        return ResponseDto.createSignal(msg, status);
    }
}
