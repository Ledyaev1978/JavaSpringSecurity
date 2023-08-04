package by.mysecurityspringgit.security;

import by.mysecurityspringgit.services.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final UsersDetailService usersDetailService;

    @Autowired
    public AuthProviderImpl(UsersDetailService usersDetailService) {
        this.usersDetailService = usersDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UsersDetails usersDetails = (UsersDetails) usersDetailService.loadUserByUsername(userName); //password from the form

        String password =  authentication.getCredentials().toString();
        if(!password.equals(usersDetails.getPassword())){
            throw  new BadCredentialsException("password incorrect !!! ");
        }
        return  new UsernamePasswordAuthenticationToken(usersDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        String username =  authentication.getName();


        return true;
    }
}
