package com.example.superhim.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.superhim.pojo.CartPojo;

public interface CartDao extends CrudRepository<CartPojo, Integer> {

	@Query(value = "select count(*) as count from cart where productsn=:productsn and mobile=:mobile and password=:password ", nativeQuery = true)
	public int CheckCart(@Param("productsn") int productsn, @Param("mobile") String mobile,@Param("password") String password);

	@Transactional
	@Modifying
	@Query(value ="update cart set count = count +:count where productsn=:productsn and mobile=:mobile and password=:password ",nativeQuery = true) 
	public int addItemIfExist(@Param("count")int count, @Param("productsn") int productsn, @Param("mobile") String mobile,@Param("password") String password);
	 
	@Override
	public <S extends CartPojo> S save(S entity);
	
	@Query(value = "select count(*) as count from cart where mobile=:mobile and password=:password ", nativeQuery = true)
	public int UpdateCartValue( @Param("mobile") String mobile,@Param("password") String password);
	
	@Query(value = "select * from cart where mobile=:mobile and password=:password ", nativeQuery = true)
	public List<CartPojo> readCart( @Param("mobile") String mobile,@Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(value = "delete from cart where productsn=:productsn", nativeQuery = true)
	public int deleteCartItem(@Param("productsn")int productsn);
	
	@Transactional
	@Modifying
	@Query(value = "delete from cart where mobile=:mobile and password=:password", nativeQuery = true)
	public int removeItem(@Param("mobile")String mobile,@Param("password") String password);
	
}
