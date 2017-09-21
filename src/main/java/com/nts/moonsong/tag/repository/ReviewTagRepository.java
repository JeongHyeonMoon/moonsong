package com.nts.moonsong.tag.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.tag.dto.ReviewTagView;
import com.nts.moonsong.tag.model.ReviewTag;

/**
 * @author Naver 송주용
 *
 */
@Repository
public interface ReviewTagRepository {
	public void insertReviewTag(ReviewTagView target);

	public List<ReviewTagView> selectReviewTagViewsByReviewId(Long reviewId);

	public boolean isExistReviewTag(ReviewTagView target);

	public void deleteReviewTag(ReviewTag target);
}
