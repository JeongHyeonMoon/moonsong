package com.nts.moonsong.comment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.comment.model.PageComment;

@Repository
public interface PageCommentRepository {
	public void insertPageCommnent(PageComment pageComment);

	public List<PageComment> selectPageComments(Long pageId);

	public void updatePageCommnent(PageComment pageComment);

	public void deletePageComment(PageComment pageComment);
}
