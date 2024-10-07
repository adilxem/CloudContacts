package com.adil.CloudContacts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adil.CloudContacts.Model.Contact;
import com.adil.CloudContacts.Services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{contactId}")
    public Contact geContact(@PathVariable String contactId) {

        return contactService.geContactById(contactId);
    }

}
