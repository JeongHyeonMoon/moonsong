package com.nts.moonsong.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.score.dto.PageScoreView;
import com.nts.moonsong.score.model.Score;
import com.nts.moonsong.score.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepository;

	public void addScore(Score score) {
		scoreRepository.insertScore(score);
	}

	public float getAverageScore(Long pageId) {
		float averageScore = 0;

		List<Score> pageScores = scoreRepository.selectScoreByPageId(pageId);

		float sum = 0;
		for (Score score : pageScores) {
			sum += score.getScore();
		}
		averageScore = sum / pageScores.size();
		return averageScore;
	}

	public List<PageScoreView> getPageRank() {
		return scoreRepository.selectPagesDesc();
	}

}
