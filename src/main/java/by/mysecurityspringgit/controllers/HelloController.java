package by.mysecurityspringgit.controllers;

import by.mysecurityspringgit.security.UsersDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/index")
    public String viewIndex(){
        return  "index";
    }
    @GetMapping("/hello")
    public String sayHello(){
        return  "hello";
    }

    /*@GetMapping("/login")
    public String viewLoginPage(){
        return  "login";
    }*/

    @GetMapping("/userInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get session object authentication
        UsersDetails personDetails = (UsersDetails) authentication.getPrincipal();
        System.out.println(personDetails.getUser());

        return  "hello";
    }
}


