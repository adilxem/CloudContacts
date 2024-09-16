package com.adil.CloudContacts.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contacts {

    @Id
    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String picture;

    @Column(length = 10000)
    private String description;

    private boolean favorite = false;


    @ManyToOne
    private User user;
}
