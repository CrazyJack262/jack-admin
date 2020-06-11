package com.jack.admin.entity.bo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt token
 *
 * @author crazyjack262
 * @date 2020-06-11 12:31
 */
public class JwtToken implements AuthenticationToken {
    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
