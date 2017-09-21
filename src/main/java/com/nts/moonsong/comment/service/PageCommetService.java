package com.nts.moonsong.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.comment.model.PageComment;
import com.nts.moonsong.comment.repository.PageCommentRepository;

@Service
public class PageCommetService {

	@Autowired
	PageCommentRepository pageCommentRepository;

	public void addPageComment(PageComment pageComment) {
		pageCommentRepository.insertPageCommnent(pageComment);
	}

	public List<PageComment> getPageComments(Long pageId) {
		return pageCommentRepository.selectPageComments(pageId);
	}

	public void modifyPageComment(PageComment pageComment) {
		pageCommentRepository.updatePageCommnent(pageComment);

	}

	public boolean removePageComment(PageComment pageComment, Long loginId) {
		if (pageComment.getWriterId() == loginId) {
			pageCommentRepository.deletePageComment(pageComment);
			return true;
		} else {
			return false;
		}
	}
}
