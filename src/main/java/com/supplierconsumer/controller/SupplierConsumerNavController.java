package com.supplierconsumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supplierconsumer.constants.Messages;
import com.supplierconsumer.model.AJAXResponse;

@Controller
public class SupplierConsumerNavController {

	@RequestMapping("/")
	public String homePage(Model model) {
		return "welcome";
	}

	@RequestMapping("/addBankPageNav")
	public String addBankNavigation(Model model) {
		return "addaccounts";
	}
	
	@RequestMapping("/viewAllRevenueSupTxnsNav")
	public String viewAllRevenueSupTxnsNav(Model model) {
		return "viewtotalrevenue";
	}
	
	
	@RequestMapping("/viewAllRevenueSupTxnsNavGraph")
	public String viewAllRevenueSupTxnsNavGraph(Model model) {
		return "viewtotalrevenuegraph";
	}
	
	@RequestMapping("/viewSupplierRevenueByProductType")
	public String viewSupplierRevenueByProductType(Model model) {
		return "viewtotalrevenuesupplierproducttype";
	}
	
	@RequestMapping("/viewAllProdsNav")
	public String viewallproducts(Model model) {
		return "viewallproducts";
	}
	
	@RequestMapping("/changePasswordAdminNav")
	public String changePasswordNav(Model model) {
		return "changepasswordadmin";
	}
	
	@RequestMapping("/changePasswordSupplierNav")
	public String changePasswordSupplierNav(Model model) {
		return "changepasswordsupplier";
	}
	
	@RequestMapping("/changePasswordCustomerNav")
	public String changePasswordCustomerNav(Model model) {
		return "changepasswordcustomer";
	}

	@RequestMapping("/myPurchaseHistoryNav")
	public String myPurchaseHistoryNav(Model model) {
		return "viewpurchasehistory";
	}

	@RequestMapping("/viewBankPageNav")
	public String viewBankNavigation(Model model) {
		return "viewaccounts";
	}
	@RequestMapping("/viewAllSupTxnsNav")
	public String viewAllSupTxnsNav(Model model) {
		return "viewalltransactions";
	}

	@RequestMapping("/supplierPage")
	public String supplierHomePage(Model model) {
		return "supplier";
	}

	@RequestMapping("/supplierProductPage")
	public String supplierProductPage(Model model) {
		return "products";
	}

	@RequestMapping("/viewSupplierProductPage")
	public String viewSupplierProductPage(Model model) {
		return "viewproducts";
	}
	
	
	
	@RequestMapping("/viewSupplierTxnData")
	public String viewSupplierTxnData(Model model) {
		return "viewsuppliertransactions";
	}

	@RequestMapping("/adminPage")
	public String adminPage(Model model) {
		return "admin";
	}

	@RequestMapping("/viewUsersPage")
	public String navUsersPage(Model model) {
		return "viewusers";
	}

	@RequestMapping("/loginUser")
	public String loginUser(Model model) {
		model.addAttribute(Messages.Keys.OBJ, new AJAXResponse());
		return "login";
	}

	@RequestMapping("/registerCustomerPage")
	public String registerCustomer(Model model) {
		model.addAttribute(Messages.Keys.OBJ, new AJAXResponse());
		return "registeruser";
	}

	@RequestMapping("/registerSupplierPage")
	public String registerSupplierPage(Model model) {
		model.addAttribute(Messages.Keys.OBJ, new AJAXResponse());
		return "registersupplier";
	}

	@RequestMapping("/customerNavHomePage")
	public String viewcustomer(Model model) {
		return "customer";
	}

	@RequestMapping("/cleanUpNav")
	public String cleanUpNav(Model model) {
		return "cleanupdata";
	}

}
