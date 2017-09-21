/**
 * 
 */
package com.nts.moonsong.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.comment.model.ReviewComment;
import com.nts.moonsong.comment.repository.ReviewCommentRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class ReviewCommentService {
	@Autowired
	ReviewCommentRepository commentRepository;

	public List<ReviewComment> readCommentByReviewId(Long reviewId) {
		return commentRepository.selectCommentsByReviewId(reviewId);
	}

	public void addReviewComment(ReviewComment reviewComment) {
		commentRepository.insertReviewComment(reviewComment);
	}

	public boolean removeReviewComment(ReviewComment reviewComment, Long memberId) {
		if (memberId != reviewComment.getWriterId()) {
			return false;
		}
		commentRepository.deleteReviewComment(reviewComment);
		return true;

	}

}
