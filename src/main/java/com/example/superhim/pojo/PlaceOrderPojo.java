package com.example.superhim.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="placeorder")
public class PlaceOrderPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sn;
	private int productsn;
	private int count;
	@Column(length = 100)
	private String producttital ;
	@Column(length = 1000)
	private String productdescription ;
	@Column(length = 20)
	private	String crossprice;
	@Column (length = 20)
	private String mainprice ;
	@Column(length = 30)
	private	String availability ;
	@Column(length = 30)
	private	String department ;
	@Column(length = 30)
	private	String category ;
	@Column(length = 500)
	private String productimage;
	@Column(length = 13)
	private String mobile;
	@Column(length = 20)
	private String password;
	@Column(length = 60)
	private String orderid;
	@Column(length = 50)
	private String datetime;
	
	public PlaceOrderPojo() {
		super();
	}
	
	public PlaceOrderPojo(int productsn, int count, String producttital, String productdescription, String crossprice,
			String mainprice, String availability, String department, String category, String productimage,
			String mobile, String password, String orderid, String datetime) {
		super();
		this.productsn = productsn;
		this.count = count;
		this.producttital = producttital;
		this.productdescription = productdescription;
		this.crossprice = crossprice;
		this.mainprice = mainprice;
		this.availability = availability;
		this.department = department;
		this.category = category;
		this.productimage = productimage;
		this.mobile = mobile;
		this.password = password;
		this.orderid = orderid;
		this.datetime = datetime;
	}


	public int getSn() {
		return sn;
	}


	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getDateTime() {
		return datetime;
	}
	public void setDateTime(String datetime) {
		this.datetime = datetime;
	}


	public int getProductsn() {
		return productsn;
	}


	public void setProductsn(int productsn) {
		this.productsn = productsn;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getProducttital() {
		return producttital;
	}


	public void setProducttital(String producttital) {
		this.producttital = producttital;
	}


	public String getProductdescription() {
		return productdescription;
	}


	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}


	public String getCrossprice() {
		return crossprice;
	}


	public void setCrossprice(String crossprice) {
		this.crossprice = crossprice;
	}


	public String getMainprice() {
		return mainprice;
	}


	public void setMainprice(String mainprice) {
		this.mainprice = mainprice;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getProductimage() {
		return productimage;
	}


	public void setProductimage(String productimage) {
		this.productimage = productimage;
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
	
	
	
}
