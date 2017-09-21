package com.nts.moonsong.page.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.page.dto.PageLogView;
import com.nts.moonsong.page.dto.PageView;
import com.nts.moonsong.page.model.Page;

/**
 * @author Naver 문정현
 *
 */
@Repository
public interface PageRepository {

	public boolean isExistPageByTitle(String pageTitle);

	public void insertPage(Page target);

	public List<PageView> selectPages(@Param("startIndex") Long startIndex, @Param("offset") Long offset);

	public List<PageView> selectPageViewByKeyword(@Param("keyword") String keyword,
		@Param("startIndex") Long startIndex,
		@Param("offset") Long offset);

	public PageView selectByPageId(Long pageId);

	public void updatePage(PageView newPage);

	public void updateOwnerPage(PageView newPage);

	public void insertPageLog(@Param("page") PageView page, @Param("loginId") Long loginId);

	public void deletePage(Long pageId);

	public void downAuthority(Long pageId);

	public void deleteOwnedPage(@Param("pageId") Long pageId, @Param("loginId") Long loginId);

	public String selectContentById(Long pageId);

	public List<PageLogView> selectPageLogs(Long pageId);

	public boolean isExistRequestPage(Long pageId);

	public boolean isAuthoriedPage(Long pageId);

}
