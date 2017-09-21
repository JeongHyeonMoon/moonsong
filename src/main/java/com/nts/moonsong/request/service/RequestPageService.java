package com.nts.moonsong.request.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.moonsong.page.repository.OwnedPageRepository;
import com.nts.moonsong.request.model.RequestPage;
import com.nts.moonsong.request.repository.RequestPageRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class RequestPageService {

	@Autowired
	private RequestPageRepository requestPageRepository;

	@Autowired
	private OwnedPageRepository ownedPageRepository;

	public void addRequestPage(RequestPage requestPage) {
		requestPageRepository.insertRequestPage(requestPage);
	}

	public List<RequestPage> getRequestPages() {
		return requestPageRepository.selectRequestPages();
	}

	@Transactional
	public void addAuthorizedPage(RequestPage requestPage) {
		requestPageRepository.authorizePage(requestPage.getPageId());
		requestPageRepository.completeAuthorized(requestPage);
		ownedPageRepository.insertOwnedPage(requestPage);
	}

	public void removePageCertification(RequestPage requestPage) {
		requestPageRepository.completeAuthorized(requestPage);
	}
}
