package com.example.superhim.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminlogin")
public class AdminLoginPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sn;
	@Column(length = 30)
	private String username;
	@Column(length = 30)
	private String password;
	
	public AdminLoginPojo() {
		super();
	}
	public AdminLoginPojo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn; 
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
