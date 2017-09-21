package com.nts.moonsong.bookmark.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.bookmark.model.BookMark;
import com.nts.moonsong.page.dto.PageView;

@Repository
public interface BookMarkRepository {
	public void insertBookMark(BookMark bookMark);

	public boolean isBookMarked(BookMark bookMark);

	public void deleteBookMark(BookMark bookMark);

	public List<PageView> selectBookmarksByMemberId(@Param("loginId") Long loginId);
}
