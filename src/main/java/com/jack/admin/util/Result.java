package com.jack.admin.util;

import com.jack.admin.common.enumtype.AdminError;

import java.util.HashMap;

/**
 * 返回包装体
 *
 * @author crazyjack262
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS_CODE = 2000;
    private static final String SUCCESS_INFO = "Success!";

    public Result() {
        put("code", SUCCESS_CODE);
        put("msg", SUCCESS_INFO);
    }

    public Result(Object obj) {
        put("code", SUCCESS_CODE);
        put("msg", SUCCESS_INFO);
        put("obj", obj);
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object obj) {
        return new Result(obj);
    }

    public static Result error() {
        return error(AdminError.COMMON_ERROR);
    }

    public static Result error(String msg) {
        Result result = error(AdminError.COMMON_ERROR);
        result.put("msg", msg);
        return result;
    }

    public static Result error(AdminError AdminError) {
        Result result = new Result();
        result.put("code", AdminError.code);
        result.put("msg", AdminError.msg);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
