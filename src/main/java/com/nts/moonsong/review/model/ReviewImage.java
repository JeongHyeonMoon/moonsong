package com.nts.moonsong.review.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Naver 송주용
 *
 */
public class ReviewImage {
	private Long reviewId;
	private String imageFileName;
	private Long writerId;
	private Date regDate;
	private String imageUrlPath;
	@JsonIgnore
	private MultipartFile file;

	public static List<ReviewImage> parseMultipartFile(MultipartFile files[], Long writerId) {
		List<ReviewImage> parsedReviewImages = new ArrayList<>();
		for (MultipartFile file : files) {
			ReviewImage reviewImage = new ReviewImage();

			reviewImage.file = file;
			reviewImage.writerId = writerId;
			parsedReviewImages.add(reviewImage);
		}

		return parsedReviewImages;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
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

}
