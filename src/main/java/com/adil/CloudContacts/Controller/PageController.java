package com.adil.CloudContacts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {

        model.addAttribute("home", "hello welcome home");
        model.addAttribute("youtube", "https://www.youtube.com");
        return "home";
    }
    
    @GetMapping("/about")
    public String about() {

        return "about";
    }


    @GetMapping("/services")
    public String services() {

        return "sevices";
    }


    @GetMapping("/contactUs")
    public String contact() {

        return "contactUs";
    }


    @GetMapping("/login")
    public String login() {

        return "login";
    }


    @GetMapping("/register")
    public String register() {

        return "register";
    }
   
    
}
