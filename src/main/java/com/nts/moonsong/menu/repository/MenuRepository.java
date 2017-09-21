package com.nts.moonsong.menu.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.menu.model.MenuView;

/**
 * @author Naver 문정현
 *
 */
@Repository
public interface MenuRepository {
	public List<MenuView> selectMenus(Long pageId);

	public boolean isPageHost(@Param("menuView") MenuView menuView, @Param("loginId") Long loginId);

	public void insertMenu(MenuView menuView);

	public void updateMenu(MenuView menuView);

	public void deleteMenu(MenuView menuView);

}
