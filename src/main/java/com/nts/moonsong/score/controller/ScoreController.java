package com.nts.moonsong.score.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.score.dto.PageScoreView;
import com.nts.moonsong.score.model.Score;
import com.nts.moonsong.score.service.ScoreService;

/**
 * 
 * @author Naver 문정현
 *
 */

@Controller
@RequestMapping(value = "/api/page/score")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;

	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addScore(@RequestBody Score score, HttpServletRequest request) {
		score.setIpv6(request.getRemoteAddr());
		scoreService.addScore(score);
	}

	@GetMapping(path = "/{pageId}", produces = "application/json")
	@ResponseBody
	public float getAverageScore(@PathVariable Long pageId) {
		return scoreService.getAverageScore(pageId);
	}

	/**
	 * 1위 부터 10위 까지의 페이지를 보여줌
	 */
	@GetMapping(path = "/rank", produces = "application/json")
	@ResponseBody
	public List<PageScoreView> getPageRank() {
		return scoreService.getPageRank();
	}
}
