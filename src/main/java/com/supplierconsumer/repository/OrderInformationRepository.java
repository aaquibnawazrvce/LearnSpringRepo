package com.supplierconsumer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.OrderInformation;

@Repository
public interface OrderInformationRepository extends MongoRepository<OrderInformation, Integer> {

	Page<OrderInformation> findByLoginid(String loginid,Pageable page);

	Page<OrderInformation> findBySupplierName(String supplierName, Pageable paging);

}
