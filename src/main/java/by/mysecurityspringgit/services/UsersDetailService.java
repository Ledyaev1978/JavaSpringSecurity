package by.mysecurityspringgit.services;

import by.mysecurityspringgit.entities.Users;
import by.mysecurityspringgit.repositories.AuthoritiesRepository;
import by.mysecurityspringgit.repositories.UserRepository;
import by.mysecurityspringgit.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsersDetailService implements UserDetailsService {

    private  final UserRepository userRepository;
    private  final AuthoritiesRepository authoritiesRepository;


    @Autowired
    public UsersDetailService(UserRepository userRepository,AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
     }

    @Override
    public UserDetails loadUserByUsername(String username) {//throws UsernameNotFoundException
        Users user = userRepository.findUsersByUsername(username).get();

         if (user != null ) {
             return new UsersDetails((Users)user);
         }
         else{
             throw new UsernameNotFoundException("User not found !!!");
         }

    }

}
