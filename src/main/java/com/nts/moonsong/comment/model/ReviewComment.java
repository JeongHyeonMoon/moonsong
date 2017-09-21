/**
 * 
 */
package com.nts.moonsong.comment.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nts.moonsong.common.util.JsonDateSerializer;

/**
 * @author Naver 문정현
 *
 */
public class ReviewComment {
	private Long commentId;
	private Long writerId;
	private Long reviewId;
	private String content;
	private Date regDate;
	private String writerNickName;

	public ReviewComment() {

	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReveiwId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getWriterNickName() {
		return writerNickName;
	}

	public void setWriterNickName(String writerNickName) {
		this.writerNickName = writerNickName;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

}
