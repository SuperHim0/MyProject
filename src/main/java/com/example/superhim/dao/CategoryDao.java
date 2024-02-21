package com.example.superhim.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.AddCategoryPojo;

public interface CategoryDao extends CrudRepository<AddCategoryPojo, Integer> {
	@Transactional
	@Modifying
	@Query(value = "insert into category(department,category)values(:department,:category)",nativeQuery = true)
	public int insertCategory(@Param("department") String department, @Param("category")String category);
	
	@Query(value = "select * from category",nativeQuery = true)
	public List<AddCategoryPojo> readCategory();
}
