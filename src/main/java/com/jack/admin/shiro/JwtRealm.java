package com.jack.admin.shiro;

import com.jack.admin.entity.bo.JwtToken;
import com.jack.admin.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shiro Realm
 *
 * @author crazyjack262
 * @date 2020-06-09 12:02
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    /**
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        //判断
        if (!JwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");

    }

}