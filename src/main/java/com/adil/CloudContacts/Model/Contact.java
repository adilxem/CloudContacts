package com.adil.CloudContacts.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String picture;

    private String websiteLink;

    private String linkedinLink;

    private boolean favorite = false;


    @ManyToOne
    private User user;
}
