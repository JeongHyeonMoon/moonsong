package com.nts.moonsong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminJSPController {
	private final String prefix = "/admin";
	
	@GetMapping(path = "/members")
	public String getMembers() {
		return prefix + "/adminMembers";
	}
	@GetMapping(path = "/reports")
	public String getReports() {
		return prefix + "/adminReports";
	}
	
	@GetMapping(path = "/auth")
	public String getAuthor() {
		return prefix + "/adminAuthor";
	}
	
	@GetMapping(path = "/new")
	public String getNewAdmin() {
		return prefix + "/addAdmin";
	}
}
