package com.rockies.common.utils;

import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUpAndDown {
	private static final Logger logger = LoggerFactory.getLogger(FileUpAndDown.class);

	/** image最大size 1M */
	public final static int PRODUCTPATH_IMAGE_MAX_SIZE = 1048576;// 1048576;

	public static String fileUpload(MultipartFile f, String targetName) throws Exception {
		String message = "";
		try {
			byte[] fileArray = getFileContent(f);
			doUpload(fileArray, targetName);
			message = CommonUtils.getIPConfigBykey("imageShowPath") + targetName;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
		return message;
	}

	public static String flashfileUpload(MultipartFile f, String targetName) throws Exception {
		String message = "";
		try {
			byte[] fileArray = getFileContent(f);
			doFlashUpload(fileArray, targetName);
			message = "0";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e);
			// message = "对不起,文件上传失败了!!!!";

		}
		return message;
	}

	public static byte[] getFileContent(MultipartFile f) throws Exception {
		long fileSize = f.getSize();
		if (fileSize > Integer.MAX_VALUE) {
			throw new Exception("file too big...");
		}
		return f.getBytes();
	}

	public static void doUpload(byte[] buffer, String targetName) throws Exception {
		FileOutputStream out = new FileOutputStream(CommonUtils.getIPConfigBykey("imagePath") + targetName);
		IOUtils.write(buffer, out);
	}

	public static void doFlashUpload(byte[] buffer, String targetName) throws Exception {
		FileOutputStream out = new FileOutputStream(CommonUtils.getIPConfigBykey("flashPath") + targetName);
		out.write(buffer);
		out.close();
	}

	public static void main(String[] args) {
		// System.out.println(fileUpload(new File("D:/", "bak_5.jpg"),
		// "123.jpg"));
	}
}
