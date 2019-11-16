package com.supplierconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.OrderInformation;

@Repository
public interface SupplierRevenueRepository
		extends MongoRepository<OrderInformation, Long> {

}
