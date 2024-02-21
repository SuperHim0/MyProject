package com.example.superhim.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class AddCategoryPojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sn;
	@Column(length = 30)
	private String department ;
	@Column(length = 40)
	private String category;
	
	
	public AddCategoryPojo() {
		super();
	}

	public AddCategoryPojo(String departmentString, String categoryString) {
		super();
		this.department = departmentString;
		this.category = categoryString;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getDepartmentString() {
		return department;
	}

	public void setDepartmentString(String departmentString) {
		this.department = departmentString;
	}

	public String getCategoryString() {
		return category;
	}

	public void setCategoryString(String categoryString) {
		this.category = categoryString;
	}
	
}
