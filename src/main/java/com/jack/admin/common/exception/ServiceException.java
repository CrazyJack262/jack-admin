package com.jack.admin.common.exception;


import com.jack.admin.common.enumtype.AdminError;

/**
 * service 异常
 *
 * @author crazyJack262
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg = AdminError.COMMON_ERROR.msg;
    private int code = AdminError.COMMON_ERROR.code;

    public ServiceException(AdminError AdminError) {
        super(AdminError.msg);
        this.msg = AdminError.msg;
        this.code = AdminError.code;
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
