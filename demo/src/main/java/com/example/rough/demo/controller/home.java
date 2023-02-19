package com.example.rough.demo.controller;



// import com.smart.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.rough.demo.dao.UserRepository;
import com.example.rough.demo.entities.User;
import com.example.rough.demo.helper.message;

import jakarta.servlet.http.HttpSession;


@Controller
public class home {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
   private UserRepository userrepo;


    @RequestMapping("/")
    public String homee(Model model){
        model.addAttribute("title", "Smart Contact Manager");
        return "homee";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }
    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title", "Register - Smart Contact Manager");
        model.addAttribute("user",new User());
        return "signup";
    }

       
   //handler form registering user

   @RequestMapping(value = "/do_register" , method = RequestMethod.POST)
    public String registerUser(@Validated @ModelAttribute("user") User user ,BindingResult result1, @RequestParam(value = "agreement" , defaultValue = "false") boolean agreement , Model model,HttpSession session){

        
        try {
            
        if(!agreement)
        {
            System.out.println("You have not agreed the terms and condition");
            throw new Exception("You have not agreed the terms and condition");
        }

        if(result1.hasErrors())
        {
            model.addAttribute("user", user);
            return "signup";
        }

        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setImageurl("d.png");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println("agreement" + agreement);
        System.out.println("USER" + user);

        User result= this.userrepo.save(user);
         model.addAttribute("user",new User());
         
        //  session=request.setSession(true);
         session.setAttribute("message", new message("successsfully registered", "alert-success") );
        //  System.out.println(session.getAttribute("hello"+"message"));
        model.addAttribute("session", session);
         return "signup";
        }
         catch (Exception e) {
          e.printStackTrace();
          model.addAttribute("user",user) ;
          session.setAttribute("message",new message("Something went wrong!!" + e.getMessage(), "alert-danger") ); 
          model.addAttribute("session", session);  
          return "signup";
        }
        
    }

    
    //custom login

    @GetMapping("/signin")
    public String login(Model model){
        model.addAttribute("title", "Login page");
     return "login";
    }

}

