package com.jack.admin.shiro;

import com.jack.admin.entity.bo.JwtToken;
import com.jack.admin.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        System.out.println(jwt);
        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }
        jwt = jwt.replaceAll("Bearer ", "");
        //判断
        JwtUtil jwtUtil = new JwtUtil();
        if (!jwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }
        Claims decode = jwtUtil.decode(jwt);
        //下面是验证这个user是否是真实存在的
        log.info("在使用token登录" +  decode.get("loginName"));
        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名

    }

}