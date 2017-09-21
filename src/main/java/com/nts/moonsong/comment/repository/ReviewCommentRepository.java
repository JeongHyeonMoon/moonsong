/**
 * 
 */
package com.nts.moonsong.comment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.comment.model.ReviewComment;

/**
 * @author Naver 송주용
 *
 */
@Repository
public interface ReviewCommentRepository {

	public List<ReviewComment> selectCommentsByReviewId(Long reviewId);

	public void deleteCommentsByReviewId(@Param("review_id") Long reviewId);

	public void insertReviewComment(ReviewComment reviewComment);

	public void deleteReviewComment(ReviewComment reviewComment);

}
