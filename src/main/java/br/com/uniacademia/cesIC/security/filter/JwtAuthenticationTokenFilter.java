package br.com.uniacademia.cesIC.security.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.uniacademia.cesIC.exception.token.typeInvalid.TokenTypeInvalidException;
import br.com.uniacademia.cesIC.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = request.getHeader(this.jwtTokenUtil.getHeader());
		if (Objects.nonNull(token)) {
			if (!token.startsWith(this.jwtTokenUtil.getType())) {
				log.error("TokenInvalidTypeException - Token: {}", token);
				throw new TokenTypeInvalidException();
			}

			token = token.substring(7);

			Boolean isTokenValid = this.jwtTokenUtil.isValid(token);
			String username = this.jwtTokenUtil.getUsername(token);
			if (isTokenValid && Objects.nonNull(username)
					&& Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
}