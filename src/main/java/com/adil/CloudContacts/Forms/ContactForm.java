package com.adil.CloudContacts.Forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Name is required!")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Phone is required!")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required!")
    private String address;

    private String websiteLink;

    private String linkedinLink;

    private boolean favourite = false;

    private MultipartFile contactImage;

}
