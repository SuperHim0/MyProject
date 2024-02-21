package com.example.superhim.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.superhim.pojo.ContactPojo;

public interface ContactDao  extends CrudRepository<ContactPojo, Integer>{
	@Transactional
	@Modifying
	@Query(value = "insert into contact(name,email,message , datetime)values(:name,:email,:message,:datetime)",nativeQuery = true)
	public int insertContact (@Param("name")String name ,@Param("email") String email ,@Param("message") String messaage ,@Param("datetime")String datetime);
	
	@Query(value = "select * from contact",nativeQuery = true)
	public List<ContactPojo> readContact();
	
	@Transactional
	@Modifying
	@Query(value = "delete from contact where sn = :sn",nativeQuery = true)
	public int deleteContatc(@Param("sn") int sn);
}
