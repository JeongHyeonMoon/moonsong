package com.nts.moonsong.page.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.util.FileUtil;
import com.nts.moonsong.page.model.PageImage;
import com.nts.moonsong.page.repository.PageImageRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class PageImageService {
	private static final String SAVE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
		+ "main" + File.separator + "webapp" + File.separator + "pageImages";

	@Autowired
	private PageImageRepository pageImageRepository;

	private String convertToUrlPath(String fileName, Long pageId) {
		return "/api/pages/" + pageId + "/pageImages/" + fileName;
	}

	public static String convertToDirectoryPath(String fileName, Long pageId) {
		return SAVE_PATH + File.separator + pageId + File.separator + fileName;
	}

	public String addPageImage(Long pageId, PageImage target) throws IllegalStateException, IOException {
		FileUtil.writeFile(SAVE_PATH + File.separator + target.getPageId(), target.getImageFileName(),
			target.getFile());
		target.setPageId(pageId);
		pageImageRepository.insertPageImage(target);
		return convertToUrlPath(target.getImageFileName(), target.getPageId());

	}

	public List<PageImage> getPageImagesByPageId(Long pageId) {
		List<PageImage> pageImages = pageImageRepository.selectPageImagesByPageId(pageId);

		for (PageImage pageImage : pageImages) {
			pageImage.setImageUrlPath(convertToUrlPath(pageImage.getImageFileName(), pageId));
		}

		return pageImages;
	}

	public void removePageImageByPageId(Long pageId) throws FileDeleteFailException {
		List<PageImage> pageImages = getPageImagesByPageId(pageId);
		for (PageImage targetPageImage : pageImages) {
			FileUtil.deleteFile(convertToDirectoryPath(targetPageImage.getImageFileName(), pageId));
		}
		pageImageRepository.deletePageImagesByPageId(pageId);
	}

	public void removePageImage(PageImage target) throws FileDeleteFailException {
		pageImageRepository.deletePageImage(target);
		FileUtil.deleteFile(String.format("%s%s%d", SAVE_PATH, File.separator, target.getPageId()),
			target.getImageFileName());

	}

}
