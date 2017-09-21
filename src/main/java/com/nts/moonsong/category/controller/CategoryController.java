package com.nts.moonsong.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.category.model.Category;
import com.nts.moonsong.category.service.CategoryService;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/api/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}
}
