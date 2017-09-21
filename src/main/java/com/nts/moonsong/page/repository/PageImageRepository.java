package com.nts.moonsong.page.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.page.model.PageImage;

/**
 * 
 * @author Naver 문정현
 *
 */
@Repository
public interface PageImageRepository {

	public void insertPageImage(PageImage target);

	public List<PageImage> selectPageImagesByPageId(Long pageId);

	public void deletePageImage(PageImage target);

	public void deletePageImagesByPageId(Long pageId);

}
