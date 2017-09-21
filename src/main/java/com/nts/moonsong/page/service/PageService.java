/**
 * @author Naver 문정현
 */
package com.nts.moonsong.page.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.moonsong.common.exception.DuplicateInsertException;
import com.nts.moonsong.common.exception.InvalidAccessException;
import com.nts.moonsong.common.util.FileUtil;
import com.nts.moonsong.page.dto.PageLogView;
import com.nts.moonsong.page.dto.PageView;
import com.nts.moonsong.page.model.Page;
import com.nts.moonsong.page.model.PageImage;
import com.nts.moonsong.page.repository.PageRepository;

@Service
public class PageService {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private PageImageService pageImageService;

	public boolean isExistTitle(String initTagName) {
		return pageRepository.isExistPageByTitle(initTagName);
	}

	public void addPageView(PageView target) throws IllegalStateException, IOException, DuplicateInsertException {
		if (pageRepository.isExistPageByTitle(target.getPageTitle())) {
			throw new DuplicateInsertException();
		}
		pageRepository.insertPage(target);

		for (PageImage pageImage : target.getPageImages()) {
			pageImage.setImageFileName(String.format("%d_%d.%s", target.getWriterId(), System.currentTimeMillis(),
				FileUtil.extractExtension(pageImage.getFile().getOriginalFilename())));
			pageImageService.addPageImage(target.getPageId(), pageImage);
		}

	}

	public List<PageView> getPageViews(Long startIndex, Long offset) {
		List<PageView> pages = pageRepository.selectPages(startIndex, offset);

		for (PageView page : pages) {
			page.setPageImages(pageImageService.getPageImagesByPageId(page.getPageId()));
		}
		return pages;
	}

	public List<PageView> getPageViewByKeyword(String keyword, Long startIndex, Long offset) {
		List<PageView> pages = pageRepository.selectPageViewByKeyword(keyword, startIndex, offset);

		for (PageView page : pages) {
			page.setPageImages(pageImageService.getPageImagesByPageId(page.getPageId()));
		}
		return pages;
	}

	public PageView getPageViewByPageId(Long pageId) {
		PageView readedPage = pageRepository.selectByPageId(pageId);

		readedPage.setPageImages(pageImageService.getPageImagesByPageId(pageId));

		return readedPage;
	}

	@Transactional
	public void modifyPage(PageView page, Long loginId) throws InvalidAccessException {
		PageView target = pageRepository.selectByPageId(page.getPageId());

		if (target == null) {
			throw new InvalidAccessException();
		}

		if (target.isAuthorized()) {
			if (loginId == target.getOwnerId()) {
				pageRepository.updatePage(page);
				pageRepository.updateOwnerPage(page);
			} else {
				throw new InvalidAccessException();
			}
			pageRepository.insertPageLog(page, loginId);
		} else {
			pageRepository.updatePage(page);
			pageRepository.insertPageLog(page, loginId);
		}
	}

	public String getContentById(Long pageId) {
		return pageRepository.selectContentById(pageId);
	}

	public List<PageLogView> getPageLogs(Long pageId) {
		return pageRepository.selectPageLogs(pageId);
	}

	public String isOwnedPage(Long pageId) {
		String checkPage = null;

		if (pageRepository.isExistRequestPage(pageId)) {
			if (pageRepository.isAuthoriedPage(pageId)) {
				checkPage = "인증 완료 페이지";
			} else {
				checkPage = "인증 대기 중 페이지";
			}
		} else {
			checkPage = "인증 요청 전 페이지";
		}
		return checkPage;
	}

	/**
	 * 페이지를 만든 사람일 경우 페이지 물리 삭제 가능
	 */
	public void removePage(Long pageId, Long loginId) throws InvalidAccessException {
		Page target = pageRepository.selectByPageId(pageId);
		if (target.getWriterId() != loginId) {
			throw new InvalidAccessException();
		}
		pageRepository.deletePage(pageId);
	}

	/**
	 * 페이지 주인일 경우, 페이지 주인 권한 포기
	 */
	@Transactional
	public void downAuthority(Long pageId, Long loginId) throws InvalidAccessException {
		PageView target = pageRepository.selectByPageId(pageId);
		if (target.getOwnerId() != loginId) {
			throw new InvalidAccessException();
		}
		pageRepository.downAuthority(pageId);
		pageRepository.deleteOwnedPage(pageId, loginId);
	}

}
