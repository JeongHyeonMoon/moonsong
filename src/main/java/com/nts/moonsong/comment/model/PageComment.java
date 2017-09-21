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
public class PageComment {
	private Long pageId;
	private String writerNickName;
	private Long commentId;
	private Long writerId;
	private Date regDate;
	private String content;

	public PageComment() {

	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getWriterNickName() {
		return writerNickName;
	}

	public void setWriterNickName(String writerNickName) {
		this.writerNickName = writerNickName;
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

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
