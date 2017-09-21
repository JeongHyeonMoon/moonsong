/**
 * 
 */
package com.nts.moonsong.tag.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.tag.model.ConcernTag;
import com.nts.moonsong.tag.model.Tag;
import com.nts.moonsong.tag.service.ConcernTagService;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/api")
public class ConcernTagController {
	public static final String prefix = "tag/";

	@Autowired
	private ConcernTagService concernTagService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(path = "/members/{memberId}/tags", produces = "application/json")
	@ResponseBody
	public List<Tag> getConcernTags(@PathVariable("memberId") Long memberId) {
		return null;
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(path = "/members/{memberId}/tags", consumes = "application/json")
	@ResponseBody
	public void addConcernTag(@PathVariable("memberId") Long memberId, ConcernTag target)
		throws IOException {}
}
