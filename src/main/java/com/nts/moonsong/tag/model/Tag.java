/**
 * 
 */
package com.nts.moonsong.tag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Naver 송주용
 *
 */
public class Tag {
	protected Long tagId;
	protected String tagName;

	public Tag(String tagName) {
		this.tagName = tagName;
	}

	public Tag() {}

	/**
	 * @return the tagId
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName.trim();
	}

	@JsonIgnore
	public boolean isValid() {
		if (tagName == null || "".equals(tagName)) {
			return false;
		}
		return true;
	}
}
