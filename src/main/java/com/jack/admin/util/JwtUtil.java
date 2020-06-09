package com.jack.admin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.admin.entity.vo.SystemUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 获取JwtToken，获取JwtToken中封装的信息，判断JwtToken是否存在
 * 1. encode()，参数是=签发人，存在时间，一些其他的信息=。返回值是JwtToken对应的字符串
 * 2. decode()，参数是=JwtToken=。返回值是荷载部分的键值对
 * 3. isVerify()，参数是=JwtToken=。返回值是这个JwtToken是否存在
 *
 * @author crazyjack262
 */
public abstract class JwtUtil {

    private static final String base64EncodedSecretKey = "c3ByaW5nYm9vdHZ1ZWNyYXp5amFjazI2Mg==";

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private static final String TOKEN_PREFIX = "Bearer ";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 这里就是产生jwt字符串的地方
     * jwt字符串包括三个部分
     * 1. header
     * -当前字符串的类型，一般都是“JWT”
     * -哪种算法加密，“HS256”或者其他的加密算法
     * 所以一般都是固定的，没有什么变化
     * 2. payload
     * 一般有四个最常见的标准字段（下面有）
     * iat：签发时间，也就是这个jwt什么时候生成的
     * jti：JWT的唯一标识
     * iss：签发人，一般都是username或者userId
     * exp：过期时间
     */
    public static String encode(String iss, long ttlMillis, Map<String, Object> claims) {
        //iss签发人，ttlMillis生存时间，claims是指还想要在jwt中存储的一些非隐私信息
        if (claims == null) {
            claims = new HashMap<>();
        }
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                //2. 这个是JWT的唯一标识，一般设置成唯一的，这个方法可以生成唯一标识
                .setId(UUID.randomUUID().toString())
                //1. 这个地方就是以毫秒为单位，换算当前系统时间生成的iat
                .setIssuedAt(new Date(nowMillis))
                //3. 签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
                .setSubject(iss)
                //这个地方是生成jwt使用的算法和秘钥
                .signWith(signatureAlgorithm, base64EncodedSecretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            //4. 过期时间，这个也是使用毫秒生成的，使用当前时间+前面传入的持续时间生成
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 相当于encode的方向，传入jwtToken生成对应的username和password等字段。Claim就是一个map 也就是拿到荷载部分所有的键值对
     *
     * @param jwtToken
     * @return
     */
    public static Claims decode(String jwtToken) {
        // 得到 DefaultJwtParser
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(base64EncodedSecretKey)
                // 设置需要解析的 jwt
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * 判断jwtToken是否合法
     */
    public static boolean isVerify(String jwtToken) {
        //这个是官方的校验规则，这里只写了一个”校验算法“，可以自己加
        Algorithm algorithm;
        System.out.println(base64EncodedSecretKey);
        switch (signatureAlgorithm) {
            case HS256:
                algorithm = Algorithm.HMAC256(Base64.decodeBase64(base64EncodedSecretKey));
                break;
            default:
                throw new RuntimeException("不支持该算法");
        }
        //判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
        JWTVerifier verifier = JWT.require(algorithm).build();
        // 校验不通过会抛出异常
        verifier.verify(jwtToken);
        return true;
    }

    /**
     * jwtToken解析
     *
     * @param servletRequest
     * @return
     */
    public static String resoleToken(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String bearerToken = request.getHeader("Authorization");
        String jwt = null;
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            jwt = bearerToken.substring(7);
        } else {
            request.setAttribute("msg", "非法请求");
        }
        return jwt;
    }

    /**
     * 获取用户信息
     *
     * @param servletRequest
     * @return
     */
    public static SystemUserVo getUserInfo(ServletRequest servletRequest) {
        String jwtToken = resoleToken(servletRequest);
        Claims claims = decode(jwtToken);
        SystemUserVo vo = objectMapper.convertValue(claims, SystemUserVo.class);
        if (Objects.isNull(vo)) {
            throw new UnknownAccountException();
        }
        return vo;
    }

    public static void main(String[] args) {
        //以tom作为秘钥，以HS256加密
        Map<String, Object> map = new HashMap<>();
        map.put("username", "tom");
        map.put("password", "123456");
        map.put("age", 20);
        String jwtToken = JwtUtil.encode("tom", 30000, map);
        System.out.println(JwtUtil.isVerify(jwtToken));
        System.out.println(jwtToken);
        JwtUtil.decode(jwtToken).entrySet().forEach((entry) -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }
}