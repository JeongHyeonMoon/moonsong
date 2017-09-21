package com.nts.moonsong.member.model;

import java.sql.Date;

/**
 * 
 * @author Naver 문정현
 *
 */
public class Member {
	private Long memberId;
	private Long sectionId;
	private String nickname;
	private String password;
	private String email;
	private String loginIpv6;
	private Date regDate;

	public Member() {}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginIpv6() {
		return loginIpv6;
	}

	public void setLoginIpv6(String loginIpv6) {
		this.loginIpv6 = loginIpv6;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
