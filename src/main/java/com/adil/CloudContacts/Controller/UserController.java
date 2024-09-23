package com.adil.CloudContacts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/user")
public class UserController {


    //dashboard

    @GetMapping("/dashboard")
    public String dashboard() {


        return "user/dashboard";
    }


    //profile

    @GetMapping("/profile")
    public String profile() {


        return "user/profile";
    }


    

    //add contacts

    //view contacts

    //edit contacts

    //delete contacts

    //search contacts
}
