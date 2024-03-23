package com.restful.transactiondemo.Repository;

import com.restful.transactiondemo.Entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @Transactional
    void deleteById(Long id);       
}
