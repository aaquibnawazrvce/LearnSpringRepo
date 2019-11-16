package com.supplierconsumer.constants;

import java.net.URI;

public interface Messages {

	String INTERNAL_ERROR = "admin_error";
	String USER_NAME_MANDATORY = "username_empty_msg";
	String PASSWORD_MANDATORY = "password_empty_msg";
	String PHONENO_MANDATORY = "phoneno_empty_msg";
	String CITY_MANDATORY = "city_empty_msg";
	String STATE_MANDATORY = "state_empty_msg";
	String COUNTRY_MANDATORY = "country_empty_msg";
	String AGE_MIN_16 = "age_min_16";
	String USERNAME_PASSWORD_SAME_ERROR = "user_pass_same_error";
	String EMAIL_MANDATORY = "email_empty_msg";
	String EMAIL_INVALID_FORMAT = "email_invalid_format";
	String USERNAME_EXISTS_ERROR = "username_exists";
	String REGISTER_FAILED = "register_failed";
	String REGISTER_SUCESSFUL = "register_sucess";
	String INVALID_USER_PASSWORD_MSG = "invalid_user_pass";
	String PRODUCT_NAME_MANDATORY = "product_name_mandatory";
	String PRODUCT_DESC_MANDATORY = "product_desc_mandatory";
	String PRODUCT_TYPE_MANDATORY = "product_type_mandatory";
	String PRICE_MANDATORY = "price_mandatory";
	String FILE_MANDATORY = "file_mandatory";
	String FILE_CORRUPT = "file_corrupt";
	String FILE_NAME_MANDATORY = "file_name_mandatory";
	String CONTEXTTYPE_MANDATORY = "contexttype_mandatory";
	String PRODUCTINFO_EXISTS = "productinfo_exists";
	String SESSION_INVALID = "session_invalid";
	String ORGINALFILE_MANDATORY = "originalfile_mandatory";
	String PRODUCT_INFO_DB_FAILED = "productinfo_failed";
	String SAVE_PRODUCTINFO_SUCESS = "saveproductinfo_sucess";
	String PRODUCTTYPE_INVALID = "producttype_invalid";
	String LOGINTYPE_INVALID = "logintype_invalid";
	String UPLOAD_FILE_LOCATION = "upload_filelocation";
	String PRODUCT_LIST_EMPTY = "product_list_empty";
	String PRODUCT_LIST_RETRIVED_SUCESS = "product_list_sucess";
	String NO_PRODUCTS_FOUND = "no_products_found";
	String PRODUCTID_EMPTY = "productid_empty";
	String QUANTITY_EMPTY = "quantity_empty";
	String PRICE_EMPTY = "price_empty";
	String ACCOUNTNO_EMPTY = "accountno_empty";
	String IPIN_EMPTY = "ipin_empty";
	String ACCNO_NOTFOUND = "accno_notfound";
	String INVALID_CREDENTIALS = "invalid_credentials";
	String INSUFFICENT_FUNDS = "insufficient_funds";
	String BALANCE_UPDATE_FAILED = "balance_update_failed";
	String ORDER_INFORMATION_SAVE_FAILED = "order_info_failed";
	String ORDER_SUCESS = "order_sucess";
	String DELIVERY_ADDRESS_EMPTY = "delivery_address_empty";
	String BALANCE_EMPTY = "accbalance_empty";
	String ACCOUNT_NO_EXISTS = "accno_exists";
	String ACCOUNT_NO_SAVE_FAILED = "accno_save_failed";
	String ACCOUNTNO_SAVED_SUCESS = "accno_save_sucess";
	String BANK_LIST_EMPTY = "bank_list_empty";
	String BANK_LIST_RETRIVED_SUCESS = "bank_list_sucess";
	String ORDER_LIST_EMPTY = "orderlist_empty";
	String ORDER_LIST_RETRIVED_SUCESS = "orderlist_sucess";
	String USERS_LIST_EMPTY = "user_list_empty";
	String USERS_LIST_RET_SUCESS = "user_list_ret_sucess";
	String OLDPASSWORD_EMPTY = "oldpassword_empty";
	String NEWPASSWORD_EMPTY = "newpassword_empty";
	String CONFIRMPASSWORD_EMPTY = "confirmpassword_empty";
	String CONFIRM_NEW_PASSWORD_NOT_SAME = "confirm_new_password_same";
	String OLD_PASSWORD_MATCH_FAILED = "old_password_match_failed";
	String PASSWORD_CHANGE_FAILED = "password_change_failed";
	String CHANGE_PASSWORD_SUCESS = "change_password_is_sucessful";
	String SUPPLIER_LIST_EMPTY = "supplier_list_empty";
	String SUPPLIER_LIST_RET_SUCESS = "supplier_list_ret_sucess";
	String BALANCE_INVALID = "balance_invalid";

	interface Keys {
		String OBJ = "OBJ";
		String LOGINID_SESSION = "LOGINID_SESSION";
		String LOGINTYPE_SESSION = "LOGINTYPE_SESSION";
		String APP_NAME = "APP+91-9880185386";
		int CUSTOMER_TYPE = 2;
		String FORMAT_DECIMAL = "##.##";
		int DOCTOR_TYPE = 3;
		int INSURANCE_TYPE = 4;
		String FORMAT_OF_DIGEST = "AES";
		String INSURANCE = "INSURANCE";
		String UNAPPROVED = "UNAPPROVED";
		int ADMIN_TYPE = 1;

	}

	interface Page {
		String REGISTER_PAGE = "registeruser";
		String LOGIN_PAGE = "login";
		String CLIENT_PAGE = "client";
		String ADMIN_PAGE = "admin";
		String WELCOME_PAGE = "welcome";
	}

	interface Pages {

		String CUSTOMER_PAGE = "customer";
		String LOGIN_PAGE = "login";
		String CUSTOMER_PRODUCTS_PAGE = "customerproducts";
		String BANK_PAGE = "bankinput";
		String TXN_SUCESS_PAGE = "transactionsucess";
		String ORDER_SUCESS_PAGE = "order";
		String ADD_ACCOUNT_PAGE = "addaccounts";
		String ADMIN_SUCESS="adminsucess";
		String CHANGE_PASSWORD_ADMIN_PAGE = "changepasswordadmin";
		String CHANGE_PASSWORD_CUSTOMER_PAGE = "changepasswordcustomer";
		String CHANGE_PASSWORD_SUPPLIER_PAGE = "changepasswordsupplier";
	}

	interface Session {

		String ORDERINFO_SESSION_KEY = "ORDERINFO_SESSION_KEY";

	}

}
