package com.mycompany.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.entity.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository <UserOrder, Integer>{

	public List<UserOrder> findByUorderId(String uorderId);
//	public List<UserOrder> findAllByUorderId(String uorderId);
//	public void deleteByUorderId(String uorderId);
//	public Optional<UserOrder> findByCust(String custId);
//	public Optional<UserOrder> findByDate(String date);
//	public List<UserOrder> findAllByDate(String date);
	public List<UserOrder> findAllByCustomerId(String custId);
//	public List<UserOrder> findAllByRestId(String cust);
}
