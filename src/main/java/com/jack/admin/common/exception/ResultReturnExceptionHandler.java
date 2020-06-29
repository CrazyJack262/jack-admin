package com.jack.admin.common.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.util.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.List;

/**
 * 控制器增强
 *
 * @author crazyjack262
 * @date 2019/12/11/10:37
 */
@RestControllerAdvice
public class ResultReturnExceptionHandler {

    protected static Logger log = LoggerFactory.getLogger(ResultReturnExceptionHandler.class);

    /**
     * 捕捉shiro的异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return Result.error(ErrorCode.UNAUTHORIZED);
    }

    /**
     * 捕捉UnauthorizedException
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401() {
        return Result.error(ErrorCode.UNAUTHORIZED);
    }

    /**
     * 文件上传大小异常
     */
    @ExceptionHandler(MultipartException.class)
    public Result handleMultipart(Throwable t) {
        return Result.error(ErrorCode.COMMON_UPLOAD_FILE_SIZE_MAX);
    }

    /**
     * jackson转换Bean *
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleJsonConv(Throwable t) {
        log.info(t.getMessage(), t);
        return Result.error(ErrorCode.COMMON_PARAMS_NOT_EXIST.msg);
    }

    /**
     * 自定义异常 TokenExpiredException
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleRRException(ServiceException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public Result handleRRException(TokenExpiredException e) {
        return Result.error(ErrorCode.USER_ACCOUNT_EXPIRED);
    }

    /**
     * 参数不正常异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(Exception e) {
        log.error(exTraceBack(e), e);
        return Result.error(e.getMessage());
    }

    /**
     * 参数缺失
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleIllegalArgumentException(MissingServletRequestParameterException e) {
        log.error(exTraceBack(e), e);
        return Result.error(ErrorCode.COMMON_PARAMS_REQUIRED);
    }

    /**
     * 参数验证
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalArgumentException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            log.error("error field is : {} ,message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
        }

        return Result.error(ErrorCode.COMMON_PARAMS_ERR.code, bindingResult.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(exTraceBack(e), e);
        return Result.error("系统发生错误，请联系管理员");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(exTraceBack(e), e);
        return Result.error(ErrorCode.COMMON_REQUEST_NOT_SUPPORTED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Result handleExpiredJwtException(ExpiredJwtException e) {
        return Result.error(ErrorCode.USER_ACCOUNT_EXPIRED);
    }


    public static String exTraceBack(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            sb.append("<---");
            sb.append(String.format("[%s * %s]  ", stackTrace[i].getClassName(), stackTrace[i].getMethodName()));
        }
        sb.append(e.getMessage());
        return sb.toString();
    }
}
