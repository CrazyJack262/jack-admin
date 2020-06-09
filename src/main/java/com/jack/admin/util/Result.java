package com.jack.admin.util;

import com.jack.admin.common.enumtype.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 返回包装体
 *
 * @author crazyjack262
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS_CODE = HttpStatus.OK.value();
    private static final String SUCCESS_INFO = HttpStatus.OK.getReasonPhrase();

    private Result() {
        put("code", SUCCESS_CODE);
        put("msg", SUCCESS_INFO);
        put("data", null);
    }

    private Result(Object data) {
        put("code", SUCCESS_CODE);
        put("msg", SUCCESS_INFO);
        put("data", data);
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object obj) {
        return new Result(obj);
    }

    public static Result error() {
        return error(ErrorCode.COMMON_ERROR);
    }

    public static Result error(String msg) {
        Result result = error(ErrorCode.COMMON_ERROR);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }

    public static Result error(ErrorCode ErrorCode) {
        Result result = new Result();
        result.put("code", ErrorCode.code);
        result.put("msg", ErrorCode.msg);
        result.put("data", null);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
