package com.adil.CloudContacts.Controller;

import java.util.UUID;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.adil.CloudContacts.Forms.ContactForm;
import com.adil.CloudContacts.Helper.Constants;
import com.adil.CloudContacts.Helper.Helper;
import com.adil.CloudContacts.Helper.Message;
import com.adil.CloudContacts.Helper.MessageType;
import com.adil.CloudContacts.Model.Contact;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Services.ContactService;
import com.adil.CloudContacts.Services.ImageService;
import com.adil.CloudContacts.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    // private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @RequestMapping("/add")
    public String addContactView(Model model) {


        ContactForm contactForm = new ContactForm();

        model.addAttribute("contactForm", contactForm);

        return "/user/add-contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session) {


        // validating the add contact form:

        if (result.hasErrors()) {

            Message message = Message.builder()
            .content("Invalid Field(s)!")
            .type(MessageType.red)
            .build();

            session.setAttribute("message", message);

            return "user/add-contact";
            
        } 

        String username = Helper.getEmailOfLoggedInUser(authentication);


        // processing image

        // logger.info("file information: {}", contactForm.getContactImage().getOriginalFilename());

        String fileName = UUID.randomUUID().toString();


        String fileURL = imageService.uploadImage(contactForm.getContactImage(), fileName);



        User user = userService.getUserByEmail(username);

        Contact contact = new Contact();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedinLink(contactForm.getLinkedinLink());
        contact.setFavorite(contactForm.isFavourite());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);

        contact.setUser(user);


        contactService.save(contact);

        System.out.println(contactForm);

        Message message = Message.builder()
        .content("New Contact Added Successfully!")
        .type(MessageType.green)
        .build();

        session.setAttribute("message", message);

        return "redirect:/user/contacts/add";
    }


    @RequestMapping
    public String viewContacts(@RequestParam(value = "page", defaultValue = "0") int page, 
    @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE + "") int size, 
    @RequestParam(value = "sortBy", defaultValue = "name") String sortBy, 
    @RequestParam(value = "direction", defaultValue = "asc") String direction, 
    Model model, Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", Constants.PAGE_SIZE);

        return "/user/contacts";
    }


    @RequestMapping("/search")
    public String searchHandler(

        @RequestParam("field") String field,
        @RequestParam("keyword") String value) {


        System.out.println("field: " + field + "    keyword: " + value);

        return "user/search";
    }

}
