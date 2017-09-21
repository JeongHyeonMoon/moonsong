package com.nts.moonsong.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.menu.model.MenuView;
import com.nts.moonsong.menu.repository.MenuRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public List<MenuView> getMenus(Long pageId) {
		return menuRepository.selectMenus(pageId);
	}

	public Boolean addMenu(MenuView menuView, Long loginId) {
		if (menuRepository.isPageHost(menuView, loginId)) {
			menuRepository.insertMenu(menuView);
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyMenu(MenuView menuView, Long loginId) {
		if (menuRepository.isPageHost(menuView, loginId)) {
			menuRepository.updateMenu(menuView);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeMenu(MenuView menuView, Long loginId) {
		if (menuRepository.isPageHost(menuView, loginId)) {
			menuRepository.deleteMenu(menuView);
			return true;
		} else {
			return false;
		}
	}

}
