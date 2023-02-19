package com.example.rough.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.rough.demo.entities.User;


// @Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    @Query("select u from User u where u.email = :email")
    public User getUserByusername(@Param("email") String email);
}

