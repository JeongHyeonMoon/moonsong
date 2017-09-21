package com.nts.moonsong.score.repository;

import java.util.List;

import com.nts.moonsong.score.dto.PageScoreView;
import com.nts.moonsong.score.model.Score;

public interface ScoreRepository {

	void insertScore(Score score);

	List<Score> selectScoreByPageId(Long pageId);

	List<PageScoreView> selectPagesDesc();

}
