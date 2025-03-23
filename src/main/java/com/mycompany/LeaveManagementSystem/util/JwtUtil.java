package com.mycompany.LeaveManagementSystem.util;

import com.mycompany.LeaveManagementSystem.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpiration; // thời gian hết hạn (giây)

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKey); // JJWT 0.10.7 yêu cầu secret phải là Base64
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Lấy username từ JWT
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Kiểm tra xem token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT hết hạn!");
        } catch (MalformedJwtException e) {
            System.out.println("JWT không hợp lệ!");
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("Chữ ký JWT không đúng!");
        } catch (Exception e) {
            System.out.println("Lỗi không xác định khi xác minh JWT!");
        }
        return false;
    }

}
