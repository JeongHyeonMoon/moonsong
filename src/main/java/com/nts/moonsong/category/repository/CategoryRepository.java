package com.nts.moonsong.category.repository;

import java.util.List;

import com.nts.moonsong.category.model.Category;

/**
 * @author Naver 송주용
 *
 */
public interface CategoryRepository {
	public List<Category> selectCategories();
}
