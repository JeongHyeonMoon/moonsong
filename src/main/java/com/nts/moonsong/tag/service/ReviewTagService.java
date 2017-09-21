package com.nts.moonsong.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.moonsong.tag.dto.ReviewTagView;
import com.nts.moonsong.tag.model.ReviewTag;
import com.nts.moonsong.tag.model.Tag;
import com.nts.moonsong.tag.repository.ReviewTagRepository;

/**
 * @author Naver 송주용
 *
 */

@Service
public class ReviewTagService {
	@Autowired
	private ReviewTagRepository reviewTagRepository;

	@Autowired
	private TagService tagService;

	@Transactional
	public void addReviewTag(ReviewTagView target) {
		Tag tempTag = new Tag(target.getTagName());
		tagService.addTagIfNotExist(tempTag);
		target.parseTag(tagService.getTagByTagName(tempTag.getTagName()));

		if (reviewTagRepository.isExistReviewTag(target)) {
			return;
		}
		reviewTagRepository.insertReviewTag(target);
	}

	public List<ReviewTagView> getReviewTagViewsByReviewId(Long reviewId) {
		return reviewTagRepository.selectReviewTagViewsByReviewId(reviewId);
	}

	public void removeReviewTag(ReviewTag target) {
		reviewTagRepository.deleteReviewTag(target);
	}

	public void removeReviewTagByReviewId(Long reviewId) {
		List<ReviewTagView> tags = getReviewTagViewsByReviewId(reviewId);

		for (ReviewTagView tag : tags) {
			removeReviewTag(tag);
		}
	}
}
