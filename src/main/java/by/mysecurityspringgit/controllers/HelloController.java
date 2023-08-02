package by.mysecurityspringgit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return  "hello";
    }
}
