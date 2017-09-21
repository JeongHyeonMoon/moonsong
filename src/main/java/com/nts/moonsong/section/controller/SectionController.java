package com.nts.moonsong.section.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.section.model.Section;
import com.nts.moonsong.section.service.SectionService;

@Controller
@RequestMapping(value = "/api/section")
public class SectionController {
	@Autowired
	private SectionService sectionService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<Section> getSections() {
		return sectionService.getSections();
	}
}
