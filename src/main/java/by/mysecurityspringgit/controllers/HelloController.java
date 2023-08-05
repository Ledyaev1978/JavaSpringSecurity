package by.mysecurityspringgit.controllers;

import by.mysecurityspringgit.security.UsersDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return  "hello";
    }

    @GetMapping("/userInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get session object authentication
        UsersDetails personDetails = (UsersDetails) authentication.getPrincipal();
        System.out.println(personDetails.getUser());

        return  "hello";
    }
}


