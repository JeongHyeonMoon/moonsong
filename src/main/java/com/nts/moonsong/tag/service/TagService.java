package com.nts.moonsong.tag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.tag.model.Tag;
import com.nts.moonsong.tag.repository.TagRepository;

/**
 * @author Naver 송주용
 *
 */
@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;

	public void addTagIfNotExist(Tag target) {
		if (tagRepository.isExistTagByTagName(target.getTagName())) {
			return;
		}
		tagRepository.insertTag(target);
	}

	public Tag getTagByTagName(String tagName) {
		return tagRepository.selectTagByTagName(tagName);
	}
}
