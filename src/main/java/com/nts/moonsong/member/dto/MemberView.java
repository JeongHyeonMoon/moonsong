package com.nts.moonsong.member.dto;

import com.nts.moonsong.member.model.Member;

public class MemberView extends Member {
	private String sectionName;

	public MemberView() {
		super();
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
