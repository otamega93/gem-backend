package ve.com.gem.securities.endpointauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("projectEndPointAuthenticator")
public class ProjectEndPointAuthenticator {

	public boolean hasPermissionCustomized(int dbCode) {

		if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
			return false;
		
		List<String> listOfAuthorities = new ArrayList<String>();
		listOfAuthorities.add("ROLE_ELF");
		listOfAuthorities.add("ROLE_DRAGON");
		listOfAuthorities.add("ROLE_HOBBIT");
		listOfAuthorities.add("ROLE_USER");
		//listOfAuthorities.add("ROLE_ADMIN");
		Collection<? extends GrantedAuthority> grantedAuthorityList = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
			for (String authority : listOfAuthorities) {
				System.out.println("Authority: " + authority);
				System.out.println("grantedAuthority: " + grantedAuthority);
				if (grantedAuthority.toString().equals(authority))
					return true;
				else
					continue;
			}
				
		}		
		return false;
	}
}
