package com.nts.moonsong.menu.model;

/**
 * @author Naver 문정현
 *
 */
public class MenuView {
	private Long pageId;
	private String menuName;
	private Long price;

	public MenuView() {

	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
