package ve.com.gem.securities;

import ve.com.gem.entities.Account;
import ve.com.gem.repositories.IAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by informatica on 01/01/16.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("no user found with " + username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //String[] authStrings = account.getAuthorities().split(", ");
        Iterable<String> authStrings = Splitter.on(',')
     	       .trimResults()
     	       .omitEmptyStrings()
     	       .split(account.getAuthorities());
        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }

        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}
