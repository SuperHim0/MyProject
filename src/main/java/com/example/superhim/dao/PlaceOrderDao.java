package com.example.superhim.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.superhim.pojo.PlaceOrderPojo;

public interface PlaceOrderDao extends CrudRepository<PlaceOrderPojo, Integer> {

	@Override
	public <S extends PlaceOrderPojo> S save(S entity);
	
	@Query(value = "select * from placeorder where orderid=:orderid" , nativeQuery = true)
	public List<PlaceOrderPojo> readOrderIdwise(String orderid);
	
	@Transactional
	@Modifying
	@Query(value = "delete from placeorder where sn=:sn", nativeQuery = true)
	public int deleteOrder(@Param("sn") int sn);

	@Query(value = "select count(*) as count from placeorder where orderid=:orderid" , nativeQuery = true)
	public int checkOrderExist(@Param("orderid")String orderid);
}
