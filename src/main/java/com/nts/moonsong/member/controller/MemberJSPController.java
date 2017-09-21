package com.nts.moonsong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/member")
public class MemberJSPController {

	@GetMapping(path = "/form")
	public String addMemberForm() {
		return "/member/form";
	}

	@GetMapping(path = "/myinfo")
	public String MemberInfo() {
		return "/member/myInfo";
	}

	@GetMapping(path = "/modify-password")
	public String modifyPassword() {
		return "/member/modifypw";
	}

	@GetMapping(path = "/myreviews")
	public String showMyReviews() {
		return "/member/myReviews";
	}

	@GetMapping(path = "/bookmarks")
	public String showBookmark() {
		return "/member/myBookmarks";
	}
}
