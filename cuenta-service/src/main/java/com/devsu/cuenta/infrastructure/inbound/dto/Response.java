package com.devsu.cuenta.infrastructure.inbound.dto;

public class Response<T> {

    private boolean success;
    private String message;
    private T data;

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<>(true, message, data);
    }

    public static <T> Response<T> empty(String message) {
        return new Response<>(true, message, null);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }

}
