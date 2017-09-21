package com.nts.moonsong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.member.service.MemberService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;

	@AuthCheck(level = AuthLevel.ADMIN)
	@PostMapping(value = "/exist")
	@ResponseBody
	public boolean isExistAdmin(@RequestBody Member member) {
		return memberService.isExistAdmin(member.getMemberId());
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addAdmin(@RequestBody Member member) {
		memberService.addAdmin(member);
	}
}
