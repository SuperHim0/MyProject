package com.example.superhim.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billingaddress")
public class BillingAddressPojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sn;
	@Column(length = 30)
	private String firstname;
	@Column(length = 30)
	private String country;
	@Column(length = 1000)
	private String address;
	@Column(length = 50)
	private String city ;
	@Column(length = 40)
	private String state;
	@Column(length = 10)
	private String postcode;
	@Column(length = 15)
	private String phonenumber ;
	@Column(length = 40)
	private String email ;
	@Column(length = 100)
	private String notes ;
	@Column(length = 50)
	private String datetime;
	@Column(length = 13)
	private String mobile;
	@Column(length = 20)
	private String password;
	@Column(length = 60)
	private String orderid;
	@Column(length = 10)
	private String charge;
	@Column(length = 20)
	private String isDelivered;
	
	public BillingAddressPojo() {
		super();
	}

	public BillingAddressPojo(String firstname, String country, String address, String city, String state,
			String postcode, String phonenumber, String email, String notes, String datetime, String mobile,
			String password, String orderid, String charge, String isDelivered) {
		super();
		this.firstname = firstname;
		this.country = country;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.phonenumber = phonenumber;
		this.email = email;
		this.notes = notes;
		this.datetime = datetime;
		this.mobile = mobile;
		this.password = password;
		this.orderid = orderid;
		this.charge = charge;
		this.isDelivered = isDelivered;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(String isDelivered) {
		this.isDelivered = isDelivered;
	}

	
	
}
