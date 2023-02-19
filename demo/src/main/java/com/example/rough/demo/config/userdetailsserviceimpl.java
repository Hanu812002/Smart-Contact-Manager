package com.example.rough.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.rough.demo.dao.UserRepository;
import com.example.rough.demo.entities.User;

public class userdetailsserviceimpl  implements UserDetailsService{
 
    @Autowired
    private UserRepository userepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //fetching user from database

         User user=userepo.getUserByusername(username);
          
         if(user==null)
         {
            throw new UsernameNotFoundException("couldnot find user");
         }

    
         Customuserdetails customuserdetails=new Customuserdetails(user);

        return customuserdetails;
    }
    
}
