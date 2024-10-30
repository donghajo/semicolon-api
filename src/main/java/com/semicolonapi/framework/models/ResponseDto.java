package com.semicolonapi.framework.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private int status;
    private String msg;
    private T data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Signal {
        private int status;
        private String msg;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data<T> {
        private int status;
        private String msg;
        private T data;
    }


    public static <T> ResponseDto.Data<T> createResponse(String msg, int status, T data){
        return ResponseDto.Data.<T>builder()
                .msg(msg)
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ResponseDto.Signal createSignal(String msg, int status){
        return ResponseDto.Signal.builder()
                .msg(msg)
                .status(status)
                .build();
    }
}
