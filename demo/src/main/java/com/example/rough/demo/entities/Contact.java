package com.example.rough.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int cid;
	 private String name;
	 private String seconname;
	 private String work;
	 private String email;
	 private String phone;
	//  @Override
	// public String toString() {
	// 	return "Contact [cid=" + cid + ", name=" + name + ", seconname=" + seconname + ", work=" + work + ", email="
	// 			+ email + ", phone=" + phone + ", imageurl=" + imageurl + ", desciption=" + desciption + ", user="
	// 			+ user + "]";
	// }
	private String imageurl;
	 @Column(length=500)
	 private String desciption;
	 
	 @ManyToOne
	 private User user;
	 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeconname() {
		return seconname;
	}
	public void setSeconname(String seconname) {
		this.seconname = seconname;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Contact() {
		super();
	}
}
