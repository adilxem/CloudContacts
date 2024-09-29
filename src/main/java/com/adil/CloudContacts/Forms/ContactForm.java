package com.adil.CloudContacts.Forms;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {


    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String picture;

    private String websiteLink;

    private String linkedinLink;

    private boolean favourite = false;

    private MultipartFile profileImage;

}
