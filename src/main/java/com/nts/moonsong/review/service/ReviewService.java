package com.nts.moonsong.review.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.exception.InvalidAccessException;
import com.nts.moonsong.common.exception.NotExistElementException;
import com.nts.moonsong.common.util.FileUtil;
import com.nts.moonsong.review.dto.ReviewForUpdateView;
import com.nts.moonsong.review.dto.ReviewView;
import com.nts.moonsong.review.model.Review;
import com.nts.moonsong.review.model.ReviewImage;
import com.nts.moonsong.review.repository.ReviewRepository;
import com.nts.moonsong.tag.dto.ReviewTagView;
import com.nts.moonsong.tag.model.ReviewTag;
import com.nts.moonsong.tag.service.ReviewTagService;

/**
 * 
 * @author Naver 송주용
 *
 */
@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewImageService reviewImageService;

	@Autowired
	private ReviewTagService reviewTagService;

	@Transactional
	public void addReviewView(ReviewView target) throws IllegalStateException, IOException {
		reviewRepository.insertReview(target);

		for (ReviewImage reviewImage : target.getReviewImages()) {
			reviewImage.setReviewId(target.getReviewId());
			reviewImage.setImageFileName(String.format("%d_%d.%s", target.getWriterId(), System.currentTimeMillis(),
				FileUtil.extractExtension(reviewImage.getFile().getOriginalFilename())));
			reviewImageService.addReviewImage(reviewImage);
		}
		List<ReviewTagView> tags = target.getTags();
		for (ReviewTagView tag : tags) {
			tag.setReviewId(target.getReviewId());
			reviewTagService.addReviewTag(tag);
		}
	}

	public List<ReviewView> getReviewViews(Long startIndex, Long maxLength) {
		List<ReviewView> reviews = reviewRepository.selectReviews(startIndex, maxLength);

		for (ReviewView review : reviews) {
			review.setReviewImages(reviewImageService.getReviewImagesByReviewId(review.getReviewId()));
			review.setTags(reviewTagService.getReviewTagViewsByReviewId(review.getReviewId()));
		}

		return reviews;
	}

	public List<ReviewView> getReviewViewsByMemberId(Long memberId, Long startIndex, Long offset) {
		List<ReviewView> reviews = reviewRepository.selectReviewsByMemberId(memberId, startIndex, offset);

		for (ReviewView review : reviews) {
			review.setReviewImages(reviewImageService.getReviewImagesByReviewId(review.getReviewId()));
			review.setTags(reviewTagService.getReviewTagViewsByReviewId(review.getReviewId()));
		}
		return reviews;
	}

	public List<ReviewView> getReviewViewByKeyword(String keyword, Long startIndex, Long offset) {
		List<ReviewView> reviews = reviewRepository.selectReviewsByKeyword(keyword, startIndex, offset);

		for (ReviewView review : reviews) {
			review.setReviewImages(reviewImageService.getReviewImagesByReviewId(review.getReviewId()));
			review.setTags(reviewTagService.getReviewTagViewsByReviewId(review.getReviewId()));
		}
		return reviews;
	}

	public ReviewView getReviewViewByReviewId(Long reviewId) {
		ReviewView readedReview = reviewRepository.selectByReviewId(reviewId);

		readedReview.setReviewImages(reviewImageService.getReviewImagesByReviewId(reviewId));
		readedReview.setTags(reviewTagService.getReviewTagViewsByReviewId(reviewId));

		return readedReview;
	}

	@Transactional
	public void modifyReview(ReviewForUpdateView updateValue, Long writerId)
		throws InvalidAccessException, NotExistElementException {
		Review target = reviewRepository.selectByReviewId(updateValue.getReviewId());
		if (target == null) {
			throw new NotExistElementException();
		}
		if (target.getWriterId() != writerId) {
			throw new InvalidAccessException();
		}
		reviewRepository.updateReview(updateValue);
		for (ReviewTag reviewTag : updateValue.getRemovedTags()) {
			reviewTag.setReviewId(updateValue.getReviewId());
			reviewTagService.removeReviewTag(reviewTag);
		}
		for (ReviewTagView reviewTag : updateValue.getAddedTags()) {
			reviewTag.setReviewId(target.getReviewId());
			reviewTagService.addReviewTag(reviewTag);
		}
	}

	@Transactional
	public void removeReview(Long reviewId, Long writerId) throws InvalidAccessException, FileDeleteFailException {
		Review target = reviewRepository.selectByReviewId(reviewId);
		if (target.getWriterId() != writerId) {
			throw new InvalidAccessException();
		}
		reviewRepository.deleteReview(reviewId);
		reviewImageService.removeReviewImageByReviewId(reviewId);
		reviewTagService.removeReviewTagByReviewId(reviewId);
	}

}
