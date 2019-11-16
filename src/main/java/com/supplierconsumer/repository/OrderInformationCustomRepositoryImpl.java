package com.supplierconsumer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.supplierconsumer.uiobjects.SupplierRevenue;

@Repository
public class OrderInformationCustomRepositoryImpl {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<SupplierRevenue> getTotalPurchase() {

		List<SupplierRevenue> supplierRevenueList = null;
		try {
			

			/*Aggregation agg = newAggregation(
				match(Criteria.where("_id").lt(10)),
				group("hosting").count().as("total"),
				project("total").and("hosting").previousOperation(),
				sort(Sort.Direction.DESC, "total")
					
			);*/

		} catch (Exception e) {

		}

		return supplierRevenueList;
	}

}
