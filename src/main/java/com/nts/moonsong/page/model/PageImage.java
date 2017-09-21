package com.nts.moonsong.page.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Naver 문정현
 *
 */
public class PageImage {
	private Long pageId;
	private String imageFileName;
	private Long writerId;
	private Date regDate;
	private String imageUrlPath;
	@JsonIgnore
	private MultipartFile file;
	
	public static List<PageImage> parseMultipartFile(MultipartFile files[], Long writerId) {
		List<PageImage> parsedPageImages = new ArrayList<>();
		for (MultipartFile file : files) {
			PageImage pageImage = new PageImage();

			pageImage.file = file;
			pageImage.writerId = writerId;
			parsedPageImages.add(pageImage);
		}

		return parsedPageImages;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getImageUrlPath() {
		return imageUrlPath;
	}

	public void setImageUrlPath(String imageUrlPath) {
		this.imageUrlPath = imageUrlPath;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
