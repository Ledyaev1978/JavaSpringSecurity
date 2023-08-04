package by.mysecurityspringgit.services;

import by.mysecurityspringgit.entities.Users;
import by.mysecurityspringgit.repositories.UserRepository;
import by.mysecurityspringgit.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailService implements UserDetailsService {

    private  final UserRepository userRepository;

    @Autowired
    public UsersDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
