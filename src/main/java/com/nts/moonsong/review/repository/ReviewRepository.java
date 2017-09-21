package com.nts.moonsong.review.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.review.dto.ReviewView;
import com.nts.moonsong.review.model.Review;

/**
 * 
 * @author Naver 송주용
 *
 */
@Repository
public interface ReviewRepository {
	public void insertReview(@Param("target") Review target);

	public List<ReviewView> selectReviews(@Param("startIndex") Long startIndex, @Param("offset") Long offset);

	public List<ReviewView> selectReviewsByKeyword(@Param("keyword") String keyword,
		@Param("startIndex") Long startIndex,
		@Param("offset") Long offset);

	public ReviewView selectByReviewId(Long reviewId);

	public void updateReview(@Param("target") Review target);

	public void deleteReview(Long reviewId);

	public List<ReviewView> selectReviewsByMemberId(@Param("memberId") Long memberId,
		@Param("startIndex") Long startIndex, @Param("offset") Long offset);
}
