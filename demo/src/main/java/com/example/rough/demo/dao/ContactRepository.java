package com.example.rough.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.rough.demo.entities.Contact;
import com.example.rough.demo.entities.User;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //pagination..
    //Pageable stores page properties:
    //current page
    //contact per page
    @Query("from Contact as c where c.user.id =:userId ")
    public Page<Contact> findContactsByUserId(int userId, Pageable pageable);

    //for searching, finds all the names by searching for keywords that are passed
    //only searches for the contacts that are of the current user
    // public List<Contact> findByNameContainingAndUser(String name, User user);
}
