package com.nts.moonsong.request.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.request.model.RequestPage;

/**
 * @author Naver 문정현
 *
 */
@Repository
public interface RequestPageRepository {
	void insertRequestPage(RequestPage requestPage);
	
	List<RequestPage> selectRequestPages();

	void authorizePage(Long pageId);

	void completeAuthorized(RequestPage requestPage);

	
}
