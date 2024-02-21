package com.example.superhim.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.ProductPojo;

public interface ProductDao extends CrudRepository<ProductPojo, Integer>{
	
		
	@Override
	public <S extends ProductPojo> S save(S entity);
	
	@Query(value = "select * from product limit 8", nativeQuery = true)
	public List<ProductPojo> readproduct();

	@Override
	public Iterable<ProductPojo> findAll();

	@Query(value = "select * from product where category=:category",nativeQuery = true)
	public Iterable<ProductPojo> SearchCategory(@Param("category")String category);

	@Query(value = "select * from product where department = :department",nativeQuery = true)
	public Iterable<ProductPojo> getProductDepartmentWise(@Param("department")String department);
	
	@Override
	public Optional<ProductPojo> findById(Integer id);
	
}
