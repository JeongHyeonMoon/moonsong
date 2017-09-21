package com.nts.moonsong.page.repository;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.request.model.RequestPage;

@Repository
public interface OwnedPageRepository {
	public void insertOwnedPage(RequestPage requestPage);
}
