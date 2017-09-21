package com.nts.moonsong.menu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.menu.model.MenuView;
import com.nts.moonsong.menu.service.MenuService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/api/page/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public Boolean addMenu(@RequestBody MenuView menuView, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return menuService.addMenu(menuView, loginId);
	}

	@GetMapping(value = "/get/{pageId}", produces = "application/json")
	@ResponseBody
	public List<MenuView> getMenu(@PathVariable("pageId") Long pageId) {
		return menuService.getMenus(pageId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(consumes = "application/json")
	@ResponseBody
	public boolean modifyMenu(@RequestBody MenuView menuView, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return menuService.modifyMenu(menuView, loginId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public boolean removeMenu(@RequestBody MenuView menuView, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return menuService.removeMenu(menuView, loginId);
	}
}
