package com.adil.CloudContacts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {

        model.addAttribute("home", "hello welcome home");
        model.addAttribute("youtube", "https://www.youtube.com");
        return "home";
    }
    
    @RequestMapping("/about")
    public String about() {

        return "about";
    }


    @RequestMapping("/services")
    public String services() {

        return "sevices";
    }
    
}
