package by.mysecurityspringgit.security;


import by.mysecurityspringgit.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//class обертка над сущностью Users
public class UsersDetails implements UserDetails {

    private final Users  user;


    public UsersDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // get roles from table  authorities
        return null;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
     }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Users getUser(){
       return  this.user;
    }
}
