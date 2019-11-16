package com.supplierconsumer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.supplierconsumer.constants.Messages;
import com.supplierconsumer.model.AJAXResponse;
import com.supplierconsumer.model.BankInfo;
import com.supplierconsumer.model.Message;
import com.supplierconsumer.model.ProductInfo;
import com.supplierconsumer.mongoobjects.BankInformation;
import com.supplierconsumer.mongoobjects.BankInformationVO;
import com.supplierconsumer.mongoobjects.Login;
import com.supplierconsumer.mongoobjects.OrderInformation;
import com.supplierconsumer.mongoobjects.Product;
import com.supplierconsumer.repository.BankInformationRepository;
import com.supplierconsumer.repository.LoginRepository;
import com.supplierconsumer.repository.OrderInformationRepository;
import com.supplierconsumer.repository.ProductInfoDocRepository;
import com.supplierconsumer.uiobjects.ChangePasswordVO;
import com.supplierconsumer.uiobjects.LoginUIModel;
import com.supplierconsumer.uiobjects.OrderInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@PropertySource("classpath:errormessages.properties")
public class SupplierConsumerNonRestController extends BaseController {

	@Autowired
	private Environment environment;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private ProductInfoDocRepository productInfoRepository;

	@Autowired
	private BankInformationRepository bankInformationRepository;

	@Autowired
	private OrderInformationRepository orderInformationRepository;

	@RequestMapping("/logout.do")
	public String logout(Model model, HttpServletRequest request) {
		AJAXResponse ajaxResponse = new AJAXResponse();

		try {

			ajaxResponse.setStatus(true);

			HttpSession session = request.getSession(false);
			session.invalidate();
			ajaxResponse.setStatus(true);
			model.addAttribute(Messages.Keys.OBJ, new AJAXResponse());
			return "welcome";
		} catch (Exception e) {
			ajaxResponse.setStatus(true);
			model.addAttribute(Messages.Keys.OBJ, new AJAXResponse());
			return "welcome";
		}

	}

	@RequestMapping(value = "/login.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String doLogin(HttpServletRequest request, @ModelAttribute LoginUIModel loginUIModel, Model model) {

		AJAXResponse ajaxResponse = null;

		String loginPage = "login";
		String adminPage = "admin";
		String supplierPage = "supplier";
		String consumerPage = "customer";

		try {
			ajaxResponse = new AJAXResponse();

			String userId = loginUIModel.getUserName();

			if (StringUtils.isEmpty(userId)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.USER_NAME_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String password = loginUIModel.getPassword();

			if (StringUtils.isEmpty(password)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PASSWORD_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			if (userId.equals(password)) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USERNAME_PASSWORD_SAME_ERROR));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			Login login = loginRepository.findByUserName(userId);

			if (null == login) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.INVALID_USER_PASSWORD_MSG));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			if (!password.equals(login.getPassword())) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.INVALID_USER_PASSWORD_MSG));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			HttpSession session = request.getSession(true);

			int loginType = login.getLoginType();

			session.setAttribute(Messages.Keys.LOGINID_SESSION, userId);
			session.setAttribute(Messages.Keys.LOGINTYPE_SESSION, login.getLoginType());

			if (loginType == 1) {

				return adminPage;
			} else if (loginType == 2) {
				return consumerPage;
			} else if (loginType == 3) {
				return supplierPage;
			} else {
				return loginPage;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return loginPage;
		}

	}

	@RequestMapping(value = "/addProduct.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String doProduct(HttpServletRequest request, @ModelAttribute ProductInfo productInfoModel, Model model,
			@RequestParam MultipartFile file) {

		AJAXResponse ajaxResponse = null;

		String productsPage = "products";

		String loginPage = "login";

		String sucessSupplierPage = "suppliersucess";

		try {
			ajaxResponse = new AJAXResponse();

			String productName = productInfoModel.getProductName();

			if (StringUtils.isEmpty(productName)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCT_NAME_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			String productDesc = productInfoModel.getProductDesc();

			if (StringUtils.isEmpty(productDesc)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCT_DESC_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			int productType = productInfoModel.getProductType();

			if (productType <= 0 || productType >= 7) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCT_TYPE_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;

			}

			double price = productInfoModel.getPrice();

			if (price <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRICE_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			if (null == file) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.FILE_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			byte[] bytes = file.getBytes();

			if (null == bytes) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.FILE_CORRUPT));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			String fileName = file.getName();

			if (StringUtils.isEmpty(fileName)) {

				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.FILE_NAME_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;

			}

			String contextType = file.getContentType();

			if (StringUtils.isEmpty(contextType)) {

				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.CONTEXTTYPE_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;

			}

			String originalFileName = file.getOriginalFilename();

			if (StringUtils.isEmpty(originalFileName)) {

				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ORGINALFILE_MANDATORY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String supplierId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == supplierId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			if (null == session.getAttribute(Messages.Keys.LOGINTYPE_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.LOGINTYPE_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			try {
				Integer loginTypeDB = (Integer) session.getAttribute(Messages.Keys.LOGINTYPE_SESSION);

				if (loginTypeDB != 3) {

					ajaxResponse.setStatus(false);
					Message msg = new Message();
					msg.setErrMessage(environment.getProperty(Messages.LOGINTYPE_INVALID));
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setErrMessages(ebErrors);
					model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
					return loginPage;

				}
			} catch (Exception e) {

				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.LOGINTYPE_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			Product productInfoDocDB = productInfoRepository.findByProductName(productName);

			if (productInfoDocDB != null) {

				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCTINFO_EXISTS));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			int count = 1;

			if (null == productInfoDocDB) {

				List<Product> productInfoDocsList = productInfoRepository.findAll();

				if (productInfoDocsList != null && !productInfoDocsList.isEmpty()) {

					count = productInfoDocsList.size() + 1;
				}

			}

			try {

				String location = environment.getProperty(Messages.UPLOAD_FILE_LOCATION);
				Path path = Paths.get(location + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCT_INFO_DB_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			Product productInfoDoc = new Product();
			productInfoDoc.setContextType(file.getContentType());
			productInfoDoc.setFileName(file.getName());
			productInfoDoc.setOriginalFileName(file.getOriginalFilename());
			productInfoDoc.setPrice(productInfoModel.getPrice());
			productInfoDoc.setProductDesc(productInfoModel.getProductDesc());
			productInfoDoc.setProductId(count);
			productInfoDoc.setProductName(productInfoModel.getProductName());
			productInfoDoc.setProductType(productInfoModel.getProductType());
			productInfoDoc.setSupplierName(supplierId);
			productInfoDoc.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

			Product productInfoDocDBSave = productInfoRepository.save(productInfoDoc);

			if (null == productInfoDocDBSave) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCT_INFO_DB_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPage;
			}

			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(environment.getProperty(Messages.SAVE_PRODUCTINFO_SUCESS));

			return sucessSupplierPage;

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return productsPage;
		}

	}

	@RequestMapping(value = "/specProduct.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String retriveProductsForCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String id, Model model) {

		String productsPageHome = Messages.Pages.CUSTOMER_PAGE;
		String loginPage = Messages.Pages.LOGIN_PAGE;
		String consumerproductsPage = Messages.Pages.CUSTOMER_PRODUCTS_PAGE;
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String supplierId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == supplierId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			Page<Product> productListPage = productInfoRepository.findByProductType(new Integer(id),
					createPageRequest(0, 1000, "productType"));

			if (null == productListPage || productListPage.isEmpty()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.NO_PRODUCTS_FOUND));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return productsPageHome;
			}

			List<Product> productList = productListPage.getContent();

			ajaxResponse.setModel(productList);
			ajaxResponse.setStatus(true);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);

			return consumerproductsPage;

		} catch (Exception e) {

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return productsPageHome;

		}
	}

	@RequestMapping(value = "/buyProduct.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String redirectBankPage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute OrderInfo orderInfo, Model model) {

		String customerHome = Messages.Pages.CUSTOMER_PAGE;
		String bankPage = Messages.Pages.BANK_PAGE;

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			int productId = orderInfo.getProductId();

			if (productId <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCTID_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return customerHome;
			}

			double quantity = orderInfo.getQuantity();

			if (quantity <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.QUANTITY_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return customerHome;
			}

			double productPrice = orderInfo.getPrice();
			if (productPrice <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRICE_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return customerHome;
			}

			//
			productPrice = orderInfo.getQuantity() * orderInfo.getPrice();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return customerHome;
			}

			String loginId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == loginId) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return customerHome;
			}

			orderInfo.setLoginId(loginId);

			session.setAttribute(Messages.Session.ORDERINFO_SESSION_KEY, orderInfo);

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderInfo);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return bankPage;

		} catch (Exception e) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return customerHome;
		}

	}

	@RequestMapping(value = "/banking.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String completeBuyingTransaction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute BankInfo bankInfo, Model model) {

		String loginPage = Messages.Pages.LOGIN_PAGE;

		// String txnsucess="transactionsucess";

		String txnsucess = Messages.Pages.TXN_SUCESS_PAGE;

		String customerHome = Messages.Pages.CUSTOMER_PAGE;

		String bankPage = Messages.Pages.BANK_PAGE;

		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			if (null == session.getAttribute(Messages.Session.ORDERINFO_SESSION_KEY)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			OrderInfo orderInfo = (OrderInfo) session.getAttribute(Messages.Session.ORDERINFO_SESSION_KEY);

			if (null == orderInfo) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			int productId = orderInfo.getProductId();

			if (productId <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRODUCTID_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);

				return customerHome;
			}

			double quantity = orderInfo.getQuantity();

			if (quantity <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.QUANTITY_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);

				return customerHome;
			}

			double price = orderInfo.getPrice();
			if (price <= 0) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PRICE_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);

				return customerHome;
			}

			String loginId = orderInfo.getLoginId();

			if (null == loginId || (loginId != null && loginId.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String accountNo = bankInfo.getAccountNo();
			if (null == accountNo || (accountNo != null && accountNo.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ACCOUNTNO_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			String password = bankInfo.getPassword();
			if (null == password || (password != null && password.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.IPIN_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			String deliveryaddress = bankInfo.getDeliveryaddress();

			if (null == deliveryaddress || (deliveryaddress != null && deliveryaddress.trim().length() == 0)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.DELIVERY_ADDRESS_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			bankInfo.setProductId(productId);
			bankInfo.setPrice(price);
			bankInfo.setLoginId(loginId);
			bankInfo.setQuantity(quantity);

			// Find whether Account Number is Present
			Optional<BankInformation> bankInformation = bankInformationRepository.findById(accountNo);

			if (null == bankInformation || (bankInformation != null && !bankInformation.isPresent())) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ACCNO_NOTFOUND));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			BankInformation bankInformation2 = bankInformation.get();

			System.out.println("Password = " + password);
			System.out.println("IPIN = " + bankInformation2.getIpin());

			if (!password.equals(bankInformation2.getIpin())) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.INVALID_CREDENTIALS));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			if (price > bankInformation2.getBalance()) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.INSUFFICENT_FUNDS));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;

			}

			double balance = bankInformation2.getBalance() - price;

			bankInformation2.setBalance(balance);

			BankInformation bankInformation3 = bankInformationRepository.save(bankInformation2);

			if (null == bankInformation3) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.BALANCE_UPDATE_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			long count = orderInformationRepository.count();

			OrderInformation orderInformation = new OrderInformation();
			orderInformation.setLoginid(orderInfo.getLoginId());
			orderInformation.setProductname(orderInfo.getProductName());
			orderInformation.setProducttype(orderInfo.getProductType());
			orderInformation.setQuantity(orderInfo.getQuantity());
			orderInformation.setSupplierName(orderInfo.getSupplierName());
			orderInformation.setTransactioncost(price);
			orderInformation.setOrderid(count + 1);
			orderInformation.setDeliveryaddress(deliveryaddress);
			orderInformation.setProductid(productId);

			OrderInformation orderInformation2 = orderInformationRepository.save(orderInformation);

			if (null == orderInformation2) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ORDER_INFORMATION_SAVE_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return bankPage;
			}

			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(environment.getProperty(Messages.ORDER_SUCESS));
			ajaxResponse.setModel(orderInformation2);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return txnsucess;

		} catch (Exception e) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return loginPage;
		}
	}

	@RequestMapping(value = "/addAccount.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String addAccount(HttpServletRequest request, @ModelAttribute BankInformationVO bankInformation, Model model) {

		AJAXResponse ajaxResponse = new AJAXResponse();

		String addAccountPage = Messages.Pages.ADD_ACCOUNT_PAGE;

		String adminSucessPage = Messages.Pages.ADMIN_SUCESS;

		try {

			String accountNo = bankInformation.getAccountno();

			if (StringUtils.isEmpty(accountNo)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ACCOUNTNO_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return addAccountPage;
			}

			String ipin = bankInformation.getIpin();

			if (StringUtils.isEmpty(ipin)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.IPIN_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return addAccountPage;
			}

			String balance = bankInformation.getBalance();

			if (StringUtils.isEmpty(balance)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.BALANCE_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return addAccountPage;
			}

			try{
				
				new Double(balance);
				
			}catch(Exception e){
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.BALANCE_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return addAccountPage;
			}
			
			
			
			Optional<BankInformation> bankInformation2 = bankInformationRepository.findById(accountNo);

			if (null == bankInformation2 || (!bankInformation2.isPresent())) {
				
				BankInformation bankInformation3 = new BankInformation();
				
				bankInformation3.setAccountno(bankInformation.getAccountno());
				bankInformation3.setIpin(bankInformation.getIpin());
				bankInformation3.setBalance(new Double(bankInformation.getBalance()));

				bankInformation3 = bankInformationRepository.save(bankInformation3);

				if (null == bankInformation3) {
					Message msg = new Message();
					msg.setErrMessage(environment.getProperty(Messages.ACCOUNT_NO_SAVE_FAILED));
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajaxResponse.setErrMessages(ebErrors);
					model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
					return addAccountPage;
				} else {
					ajaxResponse.setStatus(true);
					ajaxResponse.setMessage(environment.getProperty(Messages.ACCOUNTNO_SAVED_SUCESS));
					model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
					return adminSucessPage;
				}

			} else {
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.ACCOUNT_NO_EXISTS));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return addAccountPage;
			}

		} catch (Exception e) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return addAccountPage;
		}

	}

	@RequestMapping(value = "/changePassword.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String changePassword(HttpServletRequest request, @ModelAttribute ChangePasswordVO changePasswordVO,
			Model model) {

		AJAXResponse ajaxResponse = new AJAXResponse();

		String changePasswordAdminPage = Messages.Pages.CHANGE_PASSWORD_ADMIN_PAGE;

		String changePasswordCustomerPage = Messages.Pages.CHANGE_PASSWORD_CUSTOMER_PAGE;

		String changePasswordSupplierPage = Messages.Pages.CHANGE_PASSWORD_SUPPLIER_PAGE;

		String loginPage = Messages.Pages.LOGIN_PAGE;

		String pageSwitch = loginPage;

		if (changePasswordVO.getLogintype() == 1) {
			pageSwitch = changePasswordAdminPage;
		} else if (changePasswordVO.getLogintype() == 2) {
			pageSwitch = changePasswordSupplierPage;
		} else {
			pageSwitch = changePasswordCustomerPage;
		}

		String login = "";
		try {

			HttpSession session = request.getSession(false);

			if (session == null || null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			login = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == login) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(true);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String oldpassword = changePasswordVO.getOldpassword();

			if (StringUtils.isEmpty(oldpassword)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.OLDPASSWORD_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			String newPassword = changePasswordVO.getNewpassword();

			if (StringUtils.isEmpty(newPassword)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.NEWPASSWORD_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			String confirmPassword = changePasswordVO.getConfirmnewpassword();

			if (StringUtils.isEmpty(confirmPassword)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.CONFIRMPASSWORD_EMPTY));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			if (!confirmPassword.equals(newPassword)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.CONFIRM_NEW_PASSWORD_NOT_SAME));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			Login loginInformation = loginRepository.findByUserName(login);

			if (null == loginInformation) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return loginPage;
			}

			String oldPasswordDb = loginInformation.getPassword();

			if (!oldPasswordDb.equals(oldpassword)) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.OLD_PASSWORD_MATCH_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			loginInformation.setPassword(newPassword);

			Login loginDB = loginRepository.save(loginInformation);

			if (null == loginDB) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.PASSWORD_CHANGE_FAILED));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setErrMessages(ebErrors);
				model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
				return pageSwitch;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setMessage(environment.getProperty(Messages.CHANGE_PASSWORD_SUCESS));
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return pageSwitch;

		} catch (Exception e) {
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(false);
			Message msg = new Message();
			msg.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setErrMessages(ebErrors);
			model.addAttribute(Messages.Keys.OBJ, ajaxResponse);
			return loginPage;
		}

	}
}
