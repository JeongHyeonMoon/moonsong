package com.nts.moonsong.report.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nts.moonsong.common.util.JsonDateSerializer;

/**
 * @author Naver 문정현
 *
 */
public class Report {

	private Long reporterId;
	private String writer;
	private String reporter;
	private Date regDate;

	public Report() {}

	public Long getReporterId() {
		return reporterId;
	}

	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
