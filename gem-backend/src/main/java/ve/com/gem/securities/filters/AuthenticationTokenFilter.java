package ve.com.gem.securities.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_1.perty.docbuilders.toStringDocBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import ve.com.gem.securities.TokenUtils;
import ve.com.gem.securities.UserDetailServiceImpl;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Value("${gem.token.header}")
	private String tokenHeader;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// Classic JWT token auth
		 String authToken = httpRequest.getHeader(this.tokenHeader);
		 String username = this.tokenUtils.getUsernameFromToken(authToken);

		// Custom JWT in Cookie auth

//		Cookie[] cookies = {};
//		cookies = httpRequest.getCookies();
//		String authCookie = "";
//		String username = null;

//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				System.out.println("cookie name: " + cookie.getName());
//				System.out.println("cookie value: " + cookie.getValue());
//				if (!cookie.getName().equals(tokenHeader)) {
//					continue;
//				} else {
//					authCookie = cookie.getValue();
//					System.out.println("authenticationCookie: " + authCookie);
//					username = this.tokenUtils.getUsernameFromToken(authCookie);
//					System.out.println("Username: " + username);
//				}
//			}
//		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			User user = this.userDetailsService.loadUserByUsername(username);
			if (this.tokenUtils.validateToken(authToken, user)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

}
