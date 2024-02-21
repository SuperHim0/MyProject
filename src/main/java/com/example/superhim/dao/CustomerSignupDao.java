package com.example.superhim.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.SignUpPojo;

public interface CustomerSignupDao extends CrudRepository<SignUpPojo, Integer> {

	@Query(value = "select count(*) as count from signup where mobile=:mobile" ,nativeQuery = true)
	public int userMobileCheck(@Param("mobile") String mobile);
	
	
	@Override
	public <S extends SignUpPojo> S save(S entity);
	
//	 @Query(value = "select count(*) from adminlogin where username=:username and password=:password" , nativeQuery = true)
	
	@Query(value = "select count(*) as count from signup where mobile=:mobile and password=:password" ,nativeQuery = true)
	public int CustomerLoginCheck(@Param("mobile") String mobile,@Param("password") String password);

	
}
