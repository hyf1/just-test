package com.hyf.springboot.justtest.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(path = "login", method = RequestMethod.GET)
    String login() {
        return "login";
    }
}
