package com.adil.CloudContacts.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.adil.CloudContacts.Helper.ResourceNotFoundExcepiton;
import com.adil.CloudContacts.Model.Contact;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact save(Contact contact) {

        String contactId = UUID.randomUUID().toString();

        contact.setId(contactId);

        return contactRepository.save(contact);
    }


    public List<Contact> getAllContacts() {

        return contactRepository.findAll();
    }

    public Contact geContactById(String id) {

        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcepiton("Contact not found"));
    }

    public void deleteContact(String id) {

        contactRepository.deleteById(id);
    }

    public List<Contact> getByUserId(String userId) {

        return contactRepository.findByUserId(userId);
    }

    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {


        Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepository.findByUser(user, pageable);
        
    }
}
