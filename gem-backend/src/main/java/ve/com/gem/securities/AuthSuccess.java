package ve.com.gem.securities;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.runtime.JSONFunctions;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by informatica on 01/01/16.
 */
@Component
public class AuthSuccess extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("hola");
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	response.setContentType("application/json");
    	Carlos carlos = new Carlos();
    	carlos.validation="accepted";
    	carlos.description="user logged";
    	response.getWriter().print(carlos);
    	Cookie cookie = new Cookie("user", "1234");
    	cookie.setDomain("http://localhost:3000");
    	cookie.setPath("/");
    	response.addCookie(cookie);
    	
    }
}

class Carlos {
	String validation;
	String description;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{ \"validation\": \""+this.validation+ "\" }";
	}
}