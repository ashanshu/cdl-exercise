package com.cdl.price.utils;


import lombok.Getter;
import lombok.Setter;

public final class ApiResponse<T> {

    public static final int SUCCESS_CODE = 0;

    public static final int ERROR_CODE = 1;

    private static final ApiResponse SUCCESS = new ApiResponse<>(SUCCESS_CODE, "SUCCESS", null);

    private static final ApiResponse ERROR = new ApiResponse<>(ERROR_CODE, "ERROR", null);

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public ApiResponse(int code, String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(int code, String msg, T data) {
        return new ApiResponse<>(code, msg, data);
    }
}
