package com.example.rough.demo.controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.rough.demo.dao.ContactRepository;
import com.example.rough.demo.dao.UserRepository;
import com.example.rough.demo.entities.Contact;
import com.example.rough.demo.entities.User;

// import jakarta.persistence.criteria.Path;

@Controller
@RequestMapping("/user")
public class usercontroller {
    
    @Autowired
    private UserRepository userRepository;
     
    @Autowired
    ContactRepository contactRepository;


    //This method will run always
    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        //email is used as username in the application
        String email = principal.getName();
        User user = userRepository.getUserByusername(email);
        model.addAttribute("user", user);
    }

     
 //daseboard home
    @RequestMapping("/index")
    public String dashboard(Model model,Principal principal){
        String username=principal.getName();
        //get the user using database
        User user=userRepository.getUserByusername(username); 

        System.out.println(user);
        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }

    //open add
    @GetMapping("/add-contact")
    public String openAddcontactform(Model model){
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "normal/add_contact";
    }


    //process add contact form

    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contact contact,
    @RequestParam("profileImage") MultipartFile file,
    Principal principal){
        
        try{
        String name=principal.getName();
        User user=this.userRepository.getUserByusername(name);
        
        if(file.isEmpty())
        {

        }
        else{
            contact.setImageurl(file.getOriginalFilename());

            File savefile=new ClassPathResource("static/img").getFile();
            Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("uploaded");
        }

        user.getContact().add(contact);
        contact.setUser(user);
        this.userRepository.save(user);

        System.out.println(contact);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "normal/add_contact";
    }



    @GetMapping("/show_contacts/{page}")
    public String showContacts(@PathVariable("page") int page, Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.getUserByusername(email);
         System.out.println(email);
        //Pageable stores page properties:
        //current page
        //5 contact per page

        Pageable pageable = PageRequest.of(page, 5);
        Page<Contact> contacts = contactRepository.findContactsByUserId(user.getId(), pageable);
        model.addAttribute("title", "Show Contacts");
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contacts.getTotalPages());
        return "normal/show_contacts";
    }


}
