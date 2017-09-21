package com.nts.moonsong.common.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.moonsong.common.exception.FileDeleteFailException;

/**
 * 실제 파일을 생성, 삭제하는 서비스 데이터베이스에 영향을 전혀 미치지 않는다.
 * 
 * @author Naver 송주용
 *
 */
public class FileUtil {
	/**
	 * 
	 */
	private FileUtil() {}

	public static void writeFile(String directoryPath, String saveFileName, MultipartFile target)
		throws IllegalStateException, IOException {
		createDirectory(directoryPath);

		File saveFile = new File(directoryPath, saveFileName);

		target.transferTo(saveFile);
	}

	private static void createDirectory(String directoryPath) {
		File targetDirectory = new File(directoryPath);

		targetDirectory.mkdirs();
	}

	public static String extractExtension(MultipartFile targetFile) {
		String realName = targetFile.getOriginalFilename();
		return extractExtension(realName);
	}

	public static String extractExtension(String fileName) {
		return fileName.substring(fileName.indexOf('.') + 1);
	}

	public static void deleteFile(String filePath, String fileName) throws FileDeleteFailException {
		deleteFile(String.format("%s%s%s", filePath, File.separator, fileName));
	}

	public static void deleteFile(String filePath) throws FileDeleteFailException {
		File target = new File(filePath);

		if (target.delete() == false) {
			throw new FileDeleteFailException();
		}
	}

	public void deleteFiles(List<String> filePaths) throws FileDeleteFailException {
		for (String filePath : filePaths) {
			deleteFile(filePath);
		}
	}
}
