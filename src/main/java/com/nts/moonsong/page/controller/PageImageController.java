package com.nts.moonsong.page.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.util.FileUtil;
import com.nts.moonsong.page.model.PageImage;
import com.nts.moonsong.page.service.PageImageService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(path = "/api/pages")
public class PageImageController {

	@Autowired
	private PageImageService pageImageService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/{pageId}/pageImages", produces = "application/json")
	@ResponseBody
	public PageImage addPageImage(@PathVariable("pageId") Long pageId,
		@RequestParam("file") MultipartFile newFile, HttpServletRequest req)
		throws IllegalStateException, IOException {

		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		PageImage target = new PageImage();

		target.setImageFileName(String.format("%d_%d.%s", loginId, System.currentTimeMillis(),
			FileUtil.extractExtension(newFile.getOriginalFilename())));
		target.setPageId(pageId);
		target.setWriterId(loginId);
		target.setFile(newFile);
		target.setImageUrlPath(pageImageService.addPageImage(pageId, target));

		return target;
	}

	@AuthCheck(level = AuthLevel.ALL)
	@GetMapping(path = "/{pageId}/pageImages/{imageFileName.extension}")
	@ResponseBody
	public byte[] getImage(@PathVariable(name = "pageId", required = true) Long pageId,
		@PathVariable(name = "imageFileName.extension", required = true) String imageFileName) throws IOException {
		return imageFileConvertToByteArray(PageImageService.convertToDirectoryPath(imageFileName, pageId));
	}

	@AuthCheck(level = AuthLevel.ALL)
	@DeleteMapping(path = "/{pageId}/pageImages/{imageFileName.extension}")
	@ResponseBody
	public void removeReviewImage(@PathVariable("reviewId") Long reviewId,
		@PathVariable("imageFileName.extension") String imageFileName, HttpServletRequest req)
		throws IOException, FileDeleteFailException {
		if ("".equals(imageFileName)) {
			throw new IOException();
		}
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		PageImage target = new PageImage();

		target.setImageFileName(imageFileName);
		target.setWriterId(writerId);
		target.setPageId(reviewId);

		pageImageService.removePageImage(target);
	}

	private byte[] imageFileConvertToByteArray(String imageFilePath) throws IOException {
		ByteArrayOutputStream arrayBuff = new ByteArrayOutputStream();
		try (InputStream in = new FileInputStream(new File(imageFilePath));
			BufferedInputStream bin = new BufferedInputStream(in);) {

			byte[] buffer = new byte[1024];

			int len = 0;
			while ((len = bin.read(buffer)) >= 0) {
				arrayBuff.write(buffer, 0, len);
			}

		} catch (IOException e) {
			throw e;
		}
		return arrayBuff.toByteArray();
	}

}
