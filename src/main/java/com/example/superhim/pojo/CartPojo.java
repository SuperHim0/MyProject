package com.example.superhim.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sn;
	private int productsn;
	@Column(length = 10)
	private String mobile;
	@Column(length = 16)
	private String password;
	private int count;
	
	public CartPojo() {
		super();
	}

	public CartPojo(int productsn, String mobile, String password, int count) {
		super();
		this.productsn = productsn;
		this.mobile = mobile;
		this.password = password;
		this.count = count;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getProductsn() {
		return productsn;
	}

	public void setProductsn(int productsn) {
		this.productsn = productsn;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
