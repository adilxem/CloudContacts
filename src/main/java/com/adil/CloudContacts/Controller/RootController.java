package com.adil.CloudContacts.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.adil.CloudContacts.Helper.Helper;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Services.UserService;

@ControllerAdvice
public class RootController {



    @Autowired
    UserService service;


    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @ModelAttribute
    public void loggedInUserInformation(Model model, Authentication authentication) {


        if (authentication == null) return;


        System.out.println("adding logged in user info to the model");


        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("logged in: {}", username);


        User user = service.getUserByEmail(username);

        System.out.println(user);

        System.out.println(user.getName());

        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);

    }


}
