package com.nts.moonsong.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuJSPController {

	@GetMapping(value = "/menus/{pageId}")
	public String getMenusForm(@PathVariable Long pageId, Model model) {
		model.addAttribute("pageId", pageId);
		return "/page/menu";
	}

}
