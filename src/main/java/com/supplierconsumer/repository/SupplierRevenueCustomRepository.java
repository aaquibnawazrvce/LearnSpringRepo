package com.supplierconsumer.repository;

import java.util.List;

import com.supplierconsumer.uiobjects.SupplierRevenue;
import com.supplierconsumer.uiobjects.SupplierRevenueProductTypeGroup;

public interface SupplierRevenueCustomRepository {
	
	List<SupplierRevenue> obtainRevenueGroupBySupplier();

	List<SupplierRevenueProductTypeGroup> obtainRevenueGroupByProductType(String supplierName);
}
