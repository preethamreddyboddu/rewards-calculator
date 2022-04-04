package com.rewards.retailer.repository;

import java.util.Date;
import java.util.List;

import com.rewards.retailer.model.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

  List<Purchase> findAllByCustomerIdAndDateBetween(Long customerId, Date from, Date to);
}
