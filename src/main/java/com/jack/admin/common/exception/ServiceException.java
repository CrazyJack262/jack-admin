package com.jack.admin.common.exception;


import com.jack.admin.common.enumtype.ErrorCode;

/**
 * service 异常
 *
 * @author crazyJack262
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = ErrorCode.COMMON_ERROR.code;

    public ServiceException(ErrorCode ErrorCode) {
        super(ErrorCode.msg);
        this.msg = ErrorCode.msg;
        this.code = ErrorCode.code;
    }

    public ServiceException(ErrorCode ErrorCode, String msg) {
        super(msg);
        this.msg = msg;
        this.code = ErrorCode.code;
    }

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    @Deprecated
    public ServiceException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    @Deprecated
    public ServiceException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
