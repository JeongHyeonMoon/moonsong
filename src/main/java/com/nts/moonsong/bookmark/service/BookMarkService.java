package com.nts.moonsong.bookmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.bookmark.model.BookMark;
import com.nts.moonsong.bookmark.repository.BookMarkRepository;
import com.nts.moonsong.page.dto.PageView;
import com.nts.moonsong.page.service.PageImageService;

@Service
public class BookMarkService {

	@Autowired
	BookMarkRepository bookMarkRepository;

	@Autowired
	private PageImageService pageImageService;

	public void addBookMark(BookMark bookMark) {
		bookMarkRepository.insertBookMark(bookMark);
	}

	public boolean isBookMarked(Long pageId, Long loginId) {

		return bookMarkRepository.isBookMarked(new BookMark(loginId, pageId));
	}

	public void removeBookMark(BookMark bookMark) {
		bookMarkRepository.deleteBookMark(bookMark);
	}

	public List<PageView> getMyBookmarks(Long loginId) {

		List<PageView> pages = bookMarkRepository.selectBookmarksByMemberId(loginId);

		for (PageView page : pages) {
			page.setPageImages(pageImageService.getPageImagesByPageId(page.getPageId()));
		}
		return pages;
	}
}
