package com.nts.moonsong.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.category.model.Category;
import com.nts.moonsong.category.repository.CategoryRepository;

/**
 * @author Naver 송주용
 *
 */
@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getCategories() {
		return categoryRepository.selectCategories();
	}
}
