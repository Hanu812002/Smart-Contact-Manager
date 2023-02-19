package com.example.rough.demo.entities;

import java.util.ArrayList;
import java.util.List;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="USER")
public class User {
	
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
 private int id;

 @NotBlank(message = "Name field is required")
 @Size(min=2,max = 20,message = "min 2 and max 20 characters are allowed")
 private String name;
 @Column(unique = true)

 @NotBlank(message = "Email field is required")
 private String email;

 @NotBlank(message = "Password field is required and sholud be strong")
 private String password;
 private String role;
 @Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
			+ ", enabled=" + enabled + ", imageurl=" + imageurl + ", about=" + about + ", contacts=" + contacts + "]";
}
private boolean enabled;
 private String imageurl;
 @Column(length = 500)
 private String about;
 
 
 @OneToMany(cascade = CascadeType.ALL , fetch=FetchType.LAZY,mappedBy ="user")
 private List<Contact> contacts=new ArrayList<>();
 
public List<Contact> getContact() {
	return contacts;
}
public void setContact(List<Contact> contact) {
	this.contacts = contact;
}
public User() {
	super();

}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public String getImageurl() {
	return imageurl;
}
public void setImageurl(String imageurl) {
	this.imageurl = imageurl;
}
public String getAbout() {
	return about;
}
public void setAbout(String about) {
	this.about = about;
}
 
 
}

