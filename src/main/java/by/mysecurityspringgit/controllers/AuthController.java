package by.mysecurityspringgit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping("/login")
    public String customLoginPage(){
       return "login";
    }

   /*  @PostMapping("/loginmy")
    public String postIndexPage(){
        return "redirect:/index";
    }*/

}
