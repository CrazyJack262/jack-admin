package com.jack.admin.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jack.admin.entity.bo.JwtToken;
import com.jack.admin.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

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
        log.warn("isAccessAllowed 方法被调用");
        String jwt = JwtUtil.resoleToken(servletRequest);
        if (!StringUtils.hasText(jwt)) {
            return false;
        }
        try {
            JwtToken jwtToken = new JwtToken(jwt);
            // 委托 realm 进行登录认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 返回结果为true表明登录通过
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        onLoginFail(servletRequest, servletResponse);
        return false;
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
        String errMsg = msg == null ? HttpStatus.UNAUTHORIZED.getReasonPhrase() : msg.toString();
        body.put("msg", errMsg);
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
