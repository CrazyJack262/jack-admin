package com.jack.admin.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jack.admin.entity.bo.JwtToken;
import com.jack.admin.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author crazyjack262
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {

    /**
     * 处理跨域前端的OPTIONS预请求
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest, httpResponse);
            return true;
        }
        return super.preHandle(request, response);
    }

    /**
     * 1. 返回true，shiro就直接允许访问url
     * 2. 返回false，shiro才会根据onAccessDenied的方法的返回值决定是否允许访问url
     *
     * @param servletRequest
     * @param servletResponse
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        String jwt = JwtUtil.resoleToken(servletRequest);
        if (!StringUtils.hasText(jwt)) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(jwt);
        try {
            // 委托 realm 进行登录认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
        } catch (Exception e) {
            // 认证失败
            return false;
        }
        return true;
    }

    /**
     * 返回结果为true表明登录通过
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        setHeader((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        onLoginFail(servletRequest, servletResponse);
        return false;
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        // 跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "PUT,DELETE,GET,POST,OPTIONS,HEAD");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        // 防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
    /**
     * 登录失败时默认返回 401 状态码
     *
     * @param servletRequest
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletRequest servletRequest, ServletResponse response) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> body = Maps.newHashMap();
        body.put("code", HttpStatus.UNAUTHORIZED.value());
        Object msg = request.getAttribute("msg");
        String errMsg = msg == null ? "登录失效" : msg.toString();
        body.put("msg", errMsg);
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
