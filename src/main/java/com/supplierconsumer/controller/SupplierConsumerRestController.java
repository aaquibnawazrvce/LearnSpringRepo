package com.supplierconsumer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supplierconsumer.constants.Messages;
import com.supplierconsumer.model.AJAXResponse;
import com.supplierconsumer.model.Message;
import com.supplierconsumer.mongoobjects.BankInformation;
import com.supplierconsumer.mongoobjects.Login;
import com.supplierconsumer.mongoobjects.OrderInformation;
import com.supplierconsumer.mongoobjects.Product;
import com.supplierconsumer.repository.BankInformationRepository;
import com.supplierconsumer.repository.LoginRepository;
import com.supplierconsumer.repository.OrderInformationRepository;
import com.supplierconsumer.repository.ProductInfoDocRepository;
import com.supplierconsumer.repository.SupplierCustomRepository;
import com.supplierconsumer.service.SupplierRevenueCustomRepositoryImpl;
import com.supplierconsumer.uiobjects.PaginationConfigVO;
import com.supplierconsumer.uiobjects.RegisterUIModel;
import com.supplierconsumer.uiobjects.SupplierRevenue;
import com.supplierconsumer.uiobjects.SupplierRevenueProductTypeGroup;
import com.supplierconsumer.util.EmailUtil;

@RestController
@PropertySource("classpath:errormessages.properties")
public class SupplierConsumerRestController extends BaseController {

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

	@Autowired
	private SupplierRevenueCustomRepositoryImpl supplierRevenueCustomRepositoryImpl;

	@PostMapping("/register")
	public @ResponseBody AJAXResponse registerUser(@RequestBody RegisterUIModel registerUIModel) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			if (StringUtils.isEmpty(registerUIModel.getUserName())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USER_NAME_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getPassword())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PASSWORD_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getPhoneNo())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PHONENO_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getCity())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.CITY_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getState())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.STATE_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getCountry())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.COUNTRY_MANDATORY));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			int age = registerUIModel.getAge();

			if (age <= 16) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.AGE_MIN_16));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			if (StringUtils.isEmpty(registerUIModel.getEmail())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.EMAIL_MANDATORY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			EmailUtil emailUtil = new EmailUtil();

			boolean emailValue = emailUtil.validateEmail(registerUIModel.getEmail());

			if (!emailValue) {

				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setErrMessage(environment.getProperty(Messages.EMAIL_INVALID_FORMAT));
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);

				ajaxResponse.setErrMessages(ebErrors);
				return ajaxResponse;

			}

			if (registerUIModel.getUserName().equals(registerUIModel.getPassword())) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USERNAME_PASSWORD_SAME_ERROR));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			Login login = loginRepository.findByUserName(registerUIModel.getUserName());

			int count = 1;

			if (null == login) {

				List<Login> loginList = loginRepository.findAll();

				if (loginList != null && !loginList.isEmpty()) {

					count = loginList.size() + 1;
				}

			} else {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USERNAME_EXISTS_ERROR));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			Login loginSave = new Login();
			loginSave.setAge(registerUIModel.getAge());
			loginSave.setCity(registerUIModel.getCity());
			loginSave.setCountry(registerUIModel.getCountry());
			loginSave.setEmail(registerUIModel.getEmail());
			loginSave.setPassword(registerUIModel.getPassword());
			loginSave.setLoginType(registerUIModel.getLoginType());
			loginSave.setPhoneNo(registerUIModel.getPhoneNo());
			loginSave.setState(registerUIModel.getState());
			loginSave.setUserName(registerUIModel.getUserName());
			loginSave.setId(count);

			Login logindb = loginRepository.save(loginSave);

			if (null == logindb) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();

				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.REGISTER_FAILED));
				errMessages.add(errorMessagesObj);

				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			} else {

				ajaxResponse.setStatus(true);
				ajaxResponse.setMessage(environment.getProperty(Messages.REGISTER_SUCESSFUL));
				return ajaxResponse;
			}
		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();

			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);

			return ajaxResponse;
		}

	}

	@GetMapping("/retriveAllSupProducts")
	public @ResponseBody AJAXResponse retriveProducts(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);
			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("supplierName"));
			Page<Product> productsList = productInfoRepository.findBySupplierName(userId, paging);

			if (null == productsList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PRODUCT_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<Product> productListData = productsList.getContent();

			if (null == productListData) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PRODUCT_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(productListData);
			ajaxResponse.setTotal(productListData.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.PRODUCT_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveAllAccounts")
	public @ResponseBody AJAXResponse retriveAccountNos(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("accountno"));
			Page<BankInformation> bankInformationList = bankInformationRepository.findAll(paging);

			if (null == bankInformationList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.BANK_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<BankInformation> bankInformationList1 = bankInformationList.getContent();

			if (null == bankInformationList1) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.BANK_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(bankInformationList1);
			ajaxResponse.setTotal(bankInformationList1.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.BANK_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;
	}

	@GetMapping("/retrivePurchaseHistoryFromSession")
	public @ResponseBody AJAXResponse retrivePurchaseHistory(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);
			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("loginid"));
			Page<OrderInformation> orderInformationPage = orderInformationRepository.findByLoginid(userId, paging);

			if (null == orderInformationPage) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<OrderInformation> orderListData = orderInformationPage.getContent();

			if (null == orderListData) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderListData);
			ajaxResponse.setTotal(orderListData.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.ORDER_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveSuppliersPurchase")
	public @ResponseBody AJAXResponse retriveSuppliersPurchase(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);
			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("loginid"));
			Page<OrderInformation> orderInformationPage = orderInformationRepository.findBySupplierName(userId, paging);

			if (null == orderInformationPage) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<OrderInformation> orderListData = orderInformationPage.getContent();

			if (null == orderListData) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderListData);
			ajaxResponse.setTotal(orderListData.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.ORDER_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveAllUsers")
	public @ResponseBody AJAXResponse retriveUsers(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("userName"));
			Page<Login> loginPage = loginRepository.findAll(paging);

			if (null == loginPage) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USERS_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<Login> loginPageList = loginPage.getContent();

			if (null == loginPageList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.USERS_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(loginPageList);
			ajaxResponse.setTotal(loginPageList.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.USERS_LIST_RET_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveAllOrders")
	public @ResponseBody AJAXResponse retriveAllOrders(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("loginid"));
			Page<OrderInformation> orderInformationPage = orderInformationRepository.findAll(paging);

			if (null == orderInformationPage) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<OrderInformation> orderInfoList = orderInformationPage.getContent();

			if (null == orderInfoList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.ORDER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(orderInfoList);
			ajaxResponse.setTotal(orderInfoList.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.ORDER_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveAllProducts")
	public @ResponseBody AJAXResponse retriveAllProducts(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			Pageable paging = PageRequest.of(paginationConfigVO.getStart(), paginationConfigVO.getLimit(),
					Sort.by("supplierName"));
			Page<Product> productsList = productInfoRepository.findAll(paging);

			if (null == productsList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PRODUCT_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			List<Product> productListData = productsList.getContent();

			if (null == productListData) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.PRODUCT_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(productListData);
			ajaxResponse.setTotal(productListData.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.PRODUCT_LIST_RETRIVED_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}

	@GetMapping("/retriveTotalPurchase")
	public @ResponseBody AJAXResponse retriveAllTotalPurchase(HttpServletRequest request) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			List<SupplierRevenue> supplierRevenueList = supplierRevenueCustomRepositoryImpl
					.obtainRevenueGroupBySupplier();

			if (null == supplierRevenueList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SUPPLIER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(supplierRevenueList);
			ajaxResponse.setTotal(supplierRevenueList.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.SUPPLIER_LIST_RET_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}
	
	@GetMapping("/retriveTotalPurchaseBySupplier")
	public @ResponseBody AJAXResponse retriveAllTotalPurchasePerSupplier(HttpServletRequest request) {

		AJAXResponse ajaxResponse = new AJAXResponse();
		try {

			HttpSession session = request.getSession(false);

			if (null == session.getAttribute(Messages.Keys.LOGINID_SESSION)) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			String userId = (String) session.getAttribute(Messages.Keys.LOGINID_SESSION);

			if (null == userId) {

				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SESSION_INVALID));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;

			}

			List<SupplierRevenueProductTypeGroup> supplierRevenueList = supplierRevenueCustomRepositoryImpl
					.obtainRevenueGroupByProductType(userId);

			if (null == supplierRevenueList) {
				ajaxResponse.setStatus(false);
				List<Message> errMessages = new ArrayList<Message>();
				Message errorMessagesObj = new Message();
				errorMessagesObj.setErrMessage(environment.getProperty(Messages.SUPPLIER_LIST_EMPTY));
				errMessages.add(errorMessagesObj);
				ajaxResponse.setErrMessages(errMessages);
				return ajaxResponse;
			}

			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(supplierRevenueList);
			ajaxResponse.setTotal(supplierRevenueList.size());
			ajaxResponse.setMessage(environment.getProperty(Messages.SUPPLIER_LIST_RET_SUCESS));

		} catch (Exception e) {
			ajaxResponse.setStatus(false);
			List<Message> errMessages = new ArrayList<Message>();
			Message errorMessagesObj = new Message();
			errorMessagesObj.setErrMessage(environment.getProperty(Messages.INTERNAL_ERROR));
			errMessages.add(errorMessagesObj);
			ajaxResponse.setErrMessages(errMessages);
			return ajaxResponse;
		}
		return ajaxResponse;

	}
}
