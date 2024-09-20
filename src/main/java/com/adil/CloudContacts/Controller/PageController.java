package com.adil.CloudContacts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adil.CloudContacts.Helper.Message;
import com.adil.CloudContacts.Helper.MessageType;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class PageController {


    @Autowired
    UserService service;

    @RequestMapping("/home")
    public String home(Model model) {


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
    public String register(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }


    // process registration POST request


    @PostMapping("/register")
    public String registerRequest(@ModelAttribute User user, HttpSession session) {
        
        // fetch form data 

        // validate form data

        // save to database

        // message = "registration successful"

        // redirect to login page

        // User user = User.builder()

        // .name(userForm.getName())
        // .email(null)
        // .password(userForm.getPassword())
        // .build();

        Message message = Message.builder().content("Registration Successful!").type(MessageType.green).build();
        

        session.setAttribute("message", message);

        service.addUser(user);
        
        return "redirect:/register";
    }
       
    
}
