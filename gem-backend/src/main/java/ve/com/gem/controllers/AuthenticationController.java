package ve.com.gem.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Account;
import ve.com.gem.repositories.IAccountRepository;
import ve.com.gem.securities.TokenUtils;
import ve.com.gem.securities.UserDetailServiceImpl;
import ve.com.gem.securities.json.request.AuthenticationRequest;
import ve.com.gem.securities.json.response.AuthenticationResponse;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Value("${gem.token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	private IAccountRepository accountRepository;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest,
			Device device, HttpServletResponse response, HttpServletRequest request) throws AuthenticationException {

		// Perform the authentication
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-authentication so we can generate token
		User user = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.tokenUtils.generateToken(user, device);
		
		//validate token - Manuel
		if (this.tokenUtils.validateToken(token, user)) {
			
			//Send auth token via cookie - test
			//Cookie cookie = new Cookie("X-Auth-Token", token);
			//cookie.setSecure(true); //with this one it's not send because there's no https
			//cookie.setHttpOnly(true);
			//response.addCookie(cookie);
			//return new ResponseEntity<>(HttpStatus.ACCEPTED);
			//End - Test
			
			return ResponseEntity.ok(new AuthenticationResponse(token));
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//End Manuel
		
		// Return the token
		//return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Account account = accountRepository.findByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, account.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
