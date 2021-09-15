package br.com.uniacademia.cesIC.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.uniacademia.cesIC.exception.token.invalid.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@PropertySource("classpath:security.properties")
public class JwtUtil {

    @Getter
    @Value("${token.type}")
    private String type;

    @Getter
    @Value("${token.header}")
    private String header;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${claim.key.role}")
    private String role;

    @Value("${claim.key.username}")
    private String username;

    @Value("${claim.key.created}")
    private String created;

    @Value("${claim.key.audience}")
    private String audience;

    private Claims getClaimsFromToken(String token) throws TokenInvalidException {
	Claims claims = null;

	try {
	    claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	} catch (Exception e) {
	    log.warn("TokenInvalidException - Token: {}", token);
	    throw new TokenInvalidException();
	}

	return claims;
    }

    private Date getExpirationDate(String token) {
	Claims claims = this.getClaimsFromToken(token);
	return claims.getExpiration();
    }

    private String createTokenWithRoles(Map<String, Object> claims) {
	Date expirationDate = new Date(System.currentTimeMillis() + this.expiration * 1000);

	return Jwts.builder().setClaims(claims).setExpiration(expirationDate)
		.signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    public String create(UserDetails userDetails) {
	Map<String, Object> claims = new HashMap<>();
	claims.put(this.created, new Date());
	claims.put(this.username, userDetails.getUsername());

	userDetails.getAuthorities().forEach(authority -> claims.put(this.role, authority.getAuthority()));
	return this.createTokenWithRoles(claims);
    }

    public String refresh(String token) {
	Claims claims = this.getClaimsFromToken(token);
	claims.put(this.created, new Date());

	return this.createTokenWithRoles(claims);
    }

    public String getUsername(String token) {
	Claims claims = this.getClaimsFromToken(token);
	return claims.getSubject();
    }

    public Boolean isExpired(String token) {
	Date expirationDate = this.getExpirationDate(token);
	return expirationDate.before(new Date());
    }

    public Boolean isValid(String token) {
	Date expirationDate = this.getExpirationDate(token);
	return expirationDate.after(new Date());
    }
}