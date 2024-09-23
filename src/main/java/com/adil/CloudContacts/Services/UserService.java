package com.adil.CloudContacts.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adil.CloudContacts.Helper.Constants;
import com.adil.CloudContacts.Helper.ResourceNotFoundExcepiton;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());


    public User addUser(User user) {

        String id = UUID.randomUUID().toString();

        user.setId(id);

        // encode the password

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set user default role

        user.setRoleList(List.of(Constants.ROLE_USER));

        // set a default profile picture

        user.setProfilePic("/resources/static/images/user.png");

        return repository.save(user);
    }

    public Optional<User> getUserById(String id) {

        return repository.findById(id);
    }

    public User updateUser(String id, User user) {

        User user1 = repository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("USER NOT FOUND!"));

        return repository.save(user1);
    }

    public void deleteUser(String id) {

        User user1 = repository.findById(id).orElseThrow(()-> new ResourceNotFoundExcepiton("USER NOT FOUND!"));

        repository.deleteById(id);
    }

    public boolean userExists(String id, String email) {


        User user1 = repository.findById(id).orElse(null);

        return user1 != null ? true : false;
    }

}
