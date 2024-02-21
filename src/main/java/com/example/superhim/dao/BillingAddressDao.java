package com.example.superhim.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.BillingAddressPojo;

public interface BillingAddressDao extends CrudRepository<BillingAddressPojo, Integer>{
	
	@Override
	public <S extends BillingAddressPojo> S save(S entity); 
	
	@Override
	public Iterable<BillingAddressPojo> findAll();
	
	@Transactional
	@Modifying
	@Query(value = "update billingaddress set is_Delivered='Yes' where sn=:sn", nativeQuery = true)
	public int updateDelivery(@Param("sn")int sn);
	
	@Query(value = "select * from billingaddress where mobile=:mobile and password=:password" ,nativeQuery = true)
	public List<BillingAddressPojo> readUserOrder(@Param("mobile")String moblie,@Param("password")String password);
	
	@Transactional
	@Modifying
	@Query(value = "delete form billingaddress where orderid=:orderid", nativeQuery = true)
	public int deleteDetails(@Param("orderid") String orderid);
	
}
