package com.nts.moonsong.review.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.review.model.ReviewImage;

/**
 * 
 * @author Naver 송주용
 *
 */
@Repository
public interface ReviewImageRepository {
	public void insertReviewImage(@Param("target") ReviewImage target);

	public List<ReviewImage> selectReviewImagesByReviewId(Long reviewId);

	public void deleteReviewImage(@Param("target") ReviewImage target);

	public void deleteReviewImagesByReviewId(Long reviewId);
}
