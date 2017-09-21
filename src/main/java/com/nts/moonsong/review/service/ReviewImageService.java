package com.nts.moonsong.review.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.util.FileUtil;
import com.nts.moonsong.review.model.ReviewImage;
import com.nts.moonsong.review.repository.ReviewImageRepository;

/**
 * @author Naver 송주용
 *
 */
@Service
public class ReviewImageService {
	private static final String SAVE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
		+ "main" + File.separator + "webapp" + File.separator + "reviewImages";

	@Autowired
	private ReviewImageRepository reviewImageRepository;

	private String convertToUrlPath(String fileName, Long reviewId) {
		return "/api/reviews/" + reviewId + "/reviewImages/" + fileName;
	}

	public static String convertToDirectoryPath(String fileName, Long reviewId) {
		return SAVE_PATH + File.separator + reviewId + File.separator + fileName;
	}

	/**
	 * 저장한 파일의 url 경로를 리턴한다
	 * @param target
	 * @param mf
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String addReviewImage(ReviewImage target) throws IllegalStateException, IOException {
		FileUtil.writeFile(SAVE_PATH + File.separator + target.getReviewId(), target.getImageFileName(),
			target.getFile());
		reviewImageRepository.insertReviewImage(target);
		return convertToUrlPath(target.getImageFileName(), target.getReviewId());
	}

	public List<ReviewImage> getReviewImagesByReviewId(Long reviewId) {
		List<ReviewImage> reviewImages = reviewImageRepository.selectReviewImagesByReviewId(reviewId);

		for (ReviewImage reviewImage : reviewImages) {
			reviewImage.setImageUrlPath(convertToUrlPath(reviewImage.getImageFileName(), reviewId));
		}

		return reviewImages;
	}

	public void removeReviewImageByReviewId(Long reviewId) throws FileDeleteFailException {
		List<ReviewImage> reviewImages = getReviewImagesByReviewId(reviewId);
		for (ReviewImage targetReviewImage : reviewImages) {
			FileUtil.deleteFile(convertToDirectoryPath(targetReviewImage.getImageFileName(), reviewId));
		}
		reviewImageRepository.deleteReviewImagesByReviewId(reviewId);
	}

	public void removeReviewImage(ReviewImage target)
		throws FileDeleteFailException {
		reviewImageRepository.deleteReviewImage(target);
		FileUtil.deleteFile(String.format("%s%s%d", SAVE_PATH, File.separator, target.getReviewId()),
			target.getImageFileName());
	}
}
