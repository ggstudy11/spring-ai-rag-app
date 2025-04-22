package com.rag.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

/**
 * @author ggstudy11
 * @date Created in 2025/4/22 13:59
 * @description 统一响应
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public static <T> Response<T> success() {
        return new Response<>(200, "接口成功响应", null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "接口成功响应", data);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }
}
