package com.supplierconsumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import org.springframework.stereotype.Service;

import com.supplierconsumer.mongoobjects.OrderInformation;
import com.supplierconsumer.repository.SupplierRevenueCustomRepository;
import com.supplierconsumer.uiobjects.SupplierRevenue;
import com.supplierconsumer.uiobjects.SupplierRevenueProductTypeGroup;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Service
public class SupplierRevenueCustomRepositoryImpl implements SupplierRevenueCustomRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<SupplierRevenue> obtainRevenueGroupBySupplier() {
		
		GroupOperation groupOperation = getGroupOperation();
	    ProjectionOperation projectionOperation = getProjectOperation();
	    
	    return mongoTemplate.aggregate(Aggregation.newAggregation(
	            groupOperation,
	            projectionOperation
	        ), OrderInformation.class, SupplierRevenue.class).getMappedResults();
	}
	
	@Override
	public List<SupplierRevenueProductTypeGroup> obtainRevenueGroupByProductType(String supplierName){
		
		GroupOperation groupOperation = getGroupOperationProductType();
	    ProjectionOperation projectionOperation = getProjectOperationBySupplier();
	    MatchOperation matchOperation=getMatchOperation(supplierName);
	    
	    return mongoTemplate.aggregate(Aggregation.newAggregation(
	    		matchOperation,
	            groupOperation,
	            projectionOperation
	        ), OrderInformation.class, SupplierRevenueProductTypeGroup.class).getMappedResults();
	}
	
	private MatchOperation getMatchOperation(String supplierName) {
	    Criteria priceCriteria = where("supplierName").in(supplierName);
	    return match(priceCriteria);
	}
	
	private GroupOperation getGroupOperationProductType() {
	    return group("producttype")
	        .last("producttype").as("producttype")
	        .avg("transactioncost").as("avgRevenue")
	        .addToSet("producttype").as("producttype")
	        .sum("transactioncost").as("totalRevenue");
	}
	
	private ProjectionOperation getProjectOperationBySupplier() {
	    return project("producttype", "avgRevenue", "totalRevenue")
	        .and("producttype").previousOperation();
	}
	
	
	private GroupOperation getGroupOperation() {
	    return group("supplierName")
	        .last("supplierName").as("supplierName")
	        .avg("transactioncost").as("avgRevenue")
	        .addToSet("supplierName").as("supplierName")
	        .sum("transactioncost").as("totalRevenue");
	}
	
	private ProjectionOperation getProjectOperation() {
	    return project("supplierName", "avgRevenue", "totalRevenue")
	        .and("supplierName").previousOperation();
	}

}
