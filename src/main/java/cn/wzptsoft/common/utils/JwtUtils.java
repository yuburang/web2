package cn.wzptsoft.common.utils;

import cn.wzptsoft.common.exception.BizException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtils {

    private static final String secret = "tinymall";
    private static final long expire = 24 * 3600 * 1000;

    /**
     * 生成jwt token
     */
    public static String generateToken(Long userId) {
        Date nowDate = new Date();
        // 过期时间
        Date expireDate = new Date(nowDate.getTime() + expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static String getSubject(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e){
            throw new BizException("token已过期");
        } catch (Exception e) {
            throw new BizException("token错误");
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
