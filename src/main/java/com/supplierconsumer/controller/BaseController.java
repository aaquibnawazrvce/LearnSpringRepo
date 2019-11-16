package com.supplierconsumer.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseController {
	
	protected Pageable createPageRequest(int start,int end,String sortByColumnName) {
		Pageable paging = PageRequest.of(start, end,
				Sort.by(sortByColumnName));
		return paging;
	}
	
	
}
