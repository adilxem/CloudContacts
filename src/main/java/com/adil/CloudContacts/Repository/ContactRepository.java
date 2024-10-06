package com.adil.CloudContacts.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adil.CloudContacts.Model.Contact;
import com.adil.CloudContacts.Model.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {


    Page<Contact> findByUser(User user, Pageable pageable);


    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

}
