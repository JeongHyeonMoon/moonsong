package com.nts.moonsong.page.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nts.moonsong.common.util.CustomTimeDeserializer;
import com.nts.moonsong.page.model.Page;
import com.nts.moonsong.page.model.PageImage;

/**
 * @author Naver 송주용
 *
 */
public class PageView extends Page {
	private Long ownerId;
	private String address;
	private String phoneNumber;
	private Long sectionId;
	@JsonDeserialize(using = CustomTimeDeserializer.class)
	private Time startTime;
	@JsonDeserialize(using = CustomTimeDeserializer.class)
	private Time endTime;
	private List<PageImage> pageImages;

	private String writerNickname;
	private String categoryName;
	private String ownerNickname;
	private String sectionName;

	public PageView(Page page) {
		this.pageImages = new ArrayList<>();
		pageId = page.getPageId();
		pageTitle = page.getPageTitle();
		content = page.getContent();
		writerId = page.getWriterId();
		categoryId = page.getCategoryId();
		regDate = page.getRegDate();
		isAuthorized = page.isAuthorized();
	}

	public PageView() {
		this.pageImages = new ArrayList<>();
	}

	@Override
	public void setPageId(Long pageId) {
		super.setPageId(pageId);
		for (PageImage pageImage : pageImages) {
			pageImage.setPageId(pageId);
		}
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public List<PageImage> getPageImages() {
		return pageImages;
	}

	public void setPageImages(List<PageImage> pageImages) {
		this.pageImages = pageImages;
	}

	public String getWriterNickname() {
		return writerNickname;
	}

	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getOwnerNickname() {
		return ownerNickname;
	}

	public void setOwnerNickname(String ownerNickname) {
		this.ownerNickname = ownerNickname;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
