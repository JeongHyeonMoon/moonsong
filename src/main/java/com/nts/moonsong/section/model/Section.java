package com.nts.moonsong.section.model;

/**
 * @author Naver 문정현
 *
 */
public class Section {
	private int sectionId;
	private String sectionName;
	private String chgWriter;

	public Section() {}

	public Section(String sectionName, String chgWriter) {
		this.sectionName = sectionName;
		this.chgWriter = chgWriter;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getChgWriter() {
		return chgWriter;
	}

	public void setChgWriter(String chgWriter) {
		this.chgWriter = chgWriter;
	}

}
