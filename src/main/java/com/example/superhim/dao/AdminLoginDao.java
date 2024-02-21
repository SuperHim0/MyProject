package com.example.superhim.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.AdminLoginPojo;

public interface AdminLoginDao extends CrudRepository<AdminLoginPojo, Integer>{

	 @Override
	public long count();
	 
	 @Transactional
	 @Modifying
	 @Query(value = "insert into adminlogin (username,password) values (:username,:password)" , nativeQuery = true)
	 public int AdminData(@Param("username") String username,@Param("password") String password);
	 

//	 @Query(value = "insert into adminlogin (username, password) values (:username, :password)", nativeQuery = true)
//	 public int AdminData(@Param("username") String username, @Param("password") String password);
	 
	 
	 @Query(value = "select count(*) from adminlogin where username=:username and password=:password" , nativeQuery = true)
	 public int AdminLoginCheck(@Param("username") String username,@Param("password") String password);
	 
	 @Transactional
	 @Modifying
	 @Query(value = "update adminlogin set username=:username ,password=:password where sn=1" , nativeQuery = true)
	 public int UpdateAdminLogin(@Param("username") String username ,@Param("password") String password);
}
