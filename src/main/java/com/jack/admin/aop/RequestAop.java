package com.jack.admin.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Aop实现请求参数打印
 *
 * @author crazyjack262
 * @date 2020-06-10 10:16
 */
@Profile("prod")
@Aspect
@Slf4j
@Component
public class RequestAop {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* com.jack.admin.controller..*.*(..))")
    public void requestAop() {
    }

    @Before("requestAop()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("====================");
            log.info("Authorization: " + request.getHeader("Authorization"));
            log.info(request.getMethod() + "=>" + request.getRequestURL().toString());
            log.info("CLASS_METHOD: "
                    + joinPoint.getSignature().getDeclaringTypeName()
                    + "."
                    + joinPoint.getSignature().getName());
            log.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
            log.info("====================\n");
        }

    }


    @AfterReturning(returning = "ret", pointcut = "requestAop()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 关闭:  返回前进行内容结果日志输出
        log.info("RESPONSE: " + objectMapper.writeValueAsString(ret));
        log.info("====================\n");
    }

}
