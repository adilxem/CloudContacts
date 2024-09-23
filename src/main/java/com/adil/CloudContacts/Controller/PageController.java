package com.adil.CloudContacts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adil.CloudContacts.Forms.UserForm;
import com.adil.CloudContacts.Helper.Message;
import com.adil.CloudContacts.Helper.MessageType;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class PageController {


    @Autowired
    UserService service;

    @GetMapping("/")
    public String index() {

        return "redirect:/home";
    }

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

        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);

        return "register";
    }


    // process registration POST request


    @PostMapping("/register")
    public String registerRequest(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, User user, HttpSession session) {
        
        // fetch form data 
        // done: in @GetMapping("/register") using UserForm

        // validate form data
        // done:

        if (rBindingResult.hasErrors()) return "register";


        // save to database
        // done:

        service.addUser(user);


        // message = "registration successful"
        // done:

        Message message = Message.builder().content("Registration Successful!").type(MessageType.green).build();

        session.setAttribute("message", message);


        // redirect to login page
        // done:

        return "redirect:/user/dashboard";





        // User user = User.builder()

        // .name(userForm.getName())
        // .email(null)
        // .password(userForm.getPassword())
        // .build();        
    }

}
