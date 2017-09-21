package com.nts.moonsong.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/report")
public class ReportJSPController {

	@GetMapping(path = "/admin")
	public String showAdminReports() {
		return "/admin/adminReports";
	}

	@GetMapping(path = "/my")
	public String showMyReports() {
		return "/member/myReports";
	}

}
