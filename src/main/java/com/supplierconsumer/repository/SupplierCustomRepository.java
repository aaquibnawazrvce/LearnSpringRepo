package com.supplierconsumer.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.mongoobjects.OrderInformation;
import com.supplierconsumer.uiobjects.OrdersPerSupplier;


@Repository
public interface SupplierCustomRepository extends CrudRepository<OrderInformation,Long> {
	
	@Aggregation("{ $group : { _id : $supplierName, transactioncost : { $sum : transactioncost } } }")
	List<OrdersPerSupplier> totalOrdersPerSupplier(Sort sort);

	@Aggregation(pipeline = { "{ $match : { supplierName : ?0 } }", "{ $count : total }" })
	Long totalOrdersForCustomer(String supplierName);
}
