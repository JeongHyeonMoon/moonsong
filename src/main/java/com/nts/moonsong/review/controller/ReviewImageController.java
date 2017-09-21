package com.nts.moonsong.review.controller;

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
import com.nts.moonsong.review.model.ReviewImage;
import com.nts.moonsong.review.service.ReviewImageService;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/api/reviews")
public class ReviewImageController {
	@Autowired
	private ReviewImageService reviewImageService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(path = "/{reviewId}/reviewImages", produces = "application/json")
	@ResponseBody
	public ReviewImage addReviewImage(@PathVariable("reviewId") Long reviewId,
		@RequestParam("file") MultipartFile newFile, HttpServletRequest req)
		throws IllegalStateException, IOException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		ReviewImage target = new ReviewImage();

		target.setImageFileName(String.format("%d_%d.%s", writerId, System.currentTimeMillis(),
			FileUtil.extractExtension(newFile.getOriginalFilename())));
		target.setReviewId(reviewId);
		target.setWriterId(writerId);
		target.setFile(newFile);
		target.setImageUrlPath(reviewImageService.addReviewImage(target));

		return target;
	}

	@AuthCheck(level = AuthLevel.ALL)
	@GetMapping(path = "/{reviewId}/reviewImages/{imageFileName.extension}")
	@ResponseBody
	public byte[] getImage(@PathVariable(name = "reviewId", required = true) Long reviewId,
		@PathVariable(name = "imageFileName.extension", required = true) String imageFileName) throws IOException {
		return imageFileConvertToByteArray(ReviewImageService.convertToDirectoryPath(imageFileName, reviewId));
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(path = "/{reviewId}/reviewImages/{imageFileName.extension}")
	@ResponseBody
	public void removeReviewImage(@PathVariable("reviewId") Long reviewId,
		@PathVariable("imageFileName.extension") String imageFileName, HttpServletRequest req)
		throws IOException, FileDeleteFailException {
		if ("".equals(imageFileName)) {
			throw new IOException();
		}
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		ReviewImage target = new ReviewImage();

		target.setImageFileName(imageFileName);
		target.setWriterId(writerId);
		target.setReviewId(reviewId);

		reviewImageService.removeReviewImage(target);
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
