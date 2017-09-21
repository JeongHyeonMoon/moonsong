package com.nts.moonsong.page.dto;

import java.sql.Date;

public class PageLogView extends PageView {

	public PageLogView() {}

	private Date changeDate;
	private Long changerId;
	private String changerNicname;

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Long getChangerId() {
		return changerId;
	}

	public void setChangerId(Long changerId) {
		this.changerId = changerId;
	}

	public String getChangerNicname() {
		return changerNicname;
	}

	public void setChangerNicname(String changerNicname) {
		this.changerNicname = changerNicname;
	}

}
