package com.supplierconsumer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.Product;

@Repository
public interface ProductInfoDocRepository extends MongoRepository<Product, Integer> {

	Product findByProductName(String productName);

	Page<Product> findBySupplierName(String supplierName, Pageable pageable);

	Page<Product> findByProductType(Integer productType,Pageable pageable);

}
