package com.adil.CloudContacts.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adil.CloudContacts.Helper.Constants;
import com.adil.CloudContacts.Helper.Helper;
import com.adil.CloudContacts.Helper.ResourceNotFoundExcepiton;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;



    public User addUser(User user) {


	if (repository.findByEmail(user.getEmail()).isPresent()) {

		throw new IllegalStateException("User already exists with this email!");
	}

        String id = UUID.randomUUID().toString();

        user.setId(id);

        // encode the password

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set user default role

        user.setRoleList(List.of(Constants.ROLE_USER));

        // set a default profile picture
	
        user.setProfilePic("/images/user.png");
	
	
	// String emailToken = UUID.randomUUID().toString();
	
	// user.setEmailToken(emailToken);

        User savedUser = repository.save(user);

	// String emailLink = Helper.getLinkForEmailVerification(emailToken);

	// emailService.sendEmail(savedUser.getEmail(), "Verify Your Account - CloudContacts", 
	// "Hi " + savedUser.getName() + ",\n\n" + 
	// "Click the link below to verify your account:\n\n" + 
	// emailLink + "\n\n" +
	// "Welcome to CloudContacts!\n" +
	// "Adil");

	return savedUser;
	
    }

    public Optional<User> getUserById(String id) {

        return repository.findById(id);
    }

    public User updateUser(String id, User user) {

        User user1 = repository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("USER NOT FOUND!"));

        return repository.save(user1);
    }

    public void deleteUser(String id) {

        repository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("USER NOT FOUND!"));

        repository.deleteById(id);
    }

    public boolean userExists(String id, String email) {


        User user1 = repository.findById(id).orElse(null);

        return user1 != null ? true : false;
    }


    public User getUserByEmail(String email) {

        return repository.findByEmail(email).orElse(null);
    }

//     public User getUserByEmailToken(String emailToken) {

// 	return repository.findUserByEmailToken(emailToken).orElse(null);
//     }

}
