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
import org.springframework.security.core.userdetails.UserDetails;

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

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            Date expiration = claims.getExpiration();

            // Kiểm tra xem username có khớp với userDetails không
            return username.equals(userDetails.getUsername()) && !expiration.before(new Date());

        } catch (ExpiredJwtException e) {
            System.out.println("JWT đã hết hạn: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(" JWT không hợp lệ: " + e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("Chữ ký JWT không đúng: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định khi xác minh JWT: " + e.getMessage());
        }
        return false;
    }

}
