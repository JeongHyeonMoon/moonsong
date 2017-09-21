package com.nts.moonsong.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.member.dto.MemberView;
import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.member.service.MemberService;
import com.nts.moonsong.review.dto.ReviewView;
import com.nts.moonsong.review.service.ReviewService;

/**
 * @author Naver 문정현
 *
 */

@Controller
@RequestMapping(value = "/api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ReviewService reviewService;

	@PostMapping(path = "/exist")
	@ResponseBody
	public boolean isExistNickname(@RequestBody Member member) {
		return memberService.isExistNickname(member.getNickname());
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@PostMapping(path = "/search", produces = "application/json")
	@ResponseBody
	public Member searchMemberByNickname(@RequestBody Member member) {
		return memberService.searchNickname(member.getNickname());
	}

	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addMember(@RequestBody Member member) {
		memberService.addMember(member);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<MemberView> getMembers() {
		return memberService.getMemberSections();
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(value = "/my-info", produces = "application/json")
	@ResponseBody
	public MemberView getMember(HttpServletRequest req) throws IOException {

		Long memberId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return memberService.getMemberView(memberId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(consumes = "application/json")
	@ResponseBody
	public void modifyMember(@RequestBody Member member) {
		memberService.modifyMember(member);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public void removeMember(@RequestBody Member member) {
		memberService.removeMember(member);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(value = "/myreviews", produces = "application/json")
	@ResponseBody
	public List<ReviewView> getMyReviewViews(@RequestParam(required = true, value = "start-index") Long startIndex,
		@RequestParam(required = true, value = "offset") Long offset, HttpServletRequest req) throws IOException {

		Long memberId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return reviewService.getReviewViewsByMemberId(memberId, startIndex, offset);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(value = "/password", consumes = "application/json")
	@ResponseBody
	public void modifyPassword(@RequestBody Member member, HttpServletRequest req) throws IOException {

		member.setMemberId(AuthUtil.getMemberIdFromCookie(req.getCookies()));
		memberService.modifyPassword(member);
	}
}
