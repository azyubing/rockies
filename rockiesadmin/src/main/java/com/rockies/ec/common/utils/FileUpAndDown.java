package com.rockies.ec.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUpAndDown {

	/** image最大size 1M */
	public final static int PRODUCTPATH_IMAGE_MAX_SIZE = 1048576;// 1048576;
	private static Logger logger = LoggerFactory.getLogger(FileUpAndDown.class);
	/** 压缩图片最大长度(宽或者高) */
	private final static int compressMaxLength = 412;

	public static String fileUpload(MultipartFile f, String targetName) {
		String message = "";
		targetName = targetName.replaceAll("[^.0-9a-zA-Z_\u4e00-\u9fa5]", "");
		targetName = System.currentTimeMillis()
				+ targetName.toLowerCase().trim();

		try {
			byte[] fileArray = getFileContent(f);
			doUpload(fileArray, targetName);
			message = CommonUtils.getIPConfigBykey("imageShowPath")
					+ targetName;
		} catch (Exception e) {
			message = "0";
			logger.error("上传失败图片文件失败，fileName=" + targetName, e);
		}
		return message;
	}

	public static String uploadOrderFile(MultipartFile f, String targetName) {
		String message = "";
		targetName = targetName.replaceAll("[^.0-9a-zA-Z_\u4e00-\u9fa5]", "");
		targetName = System.currentTimeMillis()
				+ targetName.toLowerCase().trim();
		try {
			byte[] fileArray = getFileContent(f);
			doUploadOrder(fileArray, targetName);
			message = CommonUtils.getIPConfigBykey("imageOrderShowPath")
					+ targetName;
		} catch (Exception e) {
			e.printStackTrace();
			message = "0";
		}
		return message;
	}

	public static String bannerMediafileUpload(MultipartFile f,
			String targetName) {
		String message = "";
		targetName = System.currentTimeMillis()
				+ targetName.toLowerCase().trim();
		try {
			byte[] fileArray = getFileContent(f);
			doBannerMediaUpload(fileArray, targetName);
			message = CommonUtils.getIPConfigBykey("bannerMediaShowPath")
					+ targetName;
		} catch (Exception e) {
			e.printStackTrace();
			message = "0";
		}
		return message;
	}

	public static String flashfileUpload(MultipartFile f, String targetName) {
		String message = "";
		try {
			byte[] fileArray = getFileContent(f);
			doFlashUpload(fileArray, targetName);
			message = "0";
		} catch (Exception e) {
			e.printStackTrace();
			message = "对不起,文件上传失败了!!!!";

		}
		return message;
	}

	public static byte[] getFileContent(MultipartFile f) throws IOException {
		long fileSize = f.getSize();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		return f.getBytes();
	}

	public static void doUpload(byte[] buffer, String targetName)
			throws Exception {
		FileOutputStream out = new FileOutputStream(
				CommonUtils.getIPConfigBykey("imagePath") + targetName);
		out.write(buffer);
		out.close();
		// 压缩图片并写入硬盘
		compressAndSavePic(buffer, targetName);
	}

	public static void compressAndSavePic(byte[] buffer, String targetName) {
		logger.info("【上传图片images2】开始压缩图片,targetName=" + targetName);
		// 压缩图片并写入硬盘
		try {
			InputStream srcImgInputStream = new ByteArrayInputStream(buffer);
			ImageCompressTools.ToSmallerPicOfMaxLength(srcImgInputStream,
					CommonUtils.getIPConfigBykey("imagePathCompress")
							+ targetName, compressMaxLength, 1.0f);
		} catch (Exception e) {
			logger.error("【上传图片images2】压缩图片出现异常,进入补救阶段,targetName="+targetName,e);
			// 复制原图片
			File destFile = new File(
					CommonUtils.getIPConfigBykey("imagePathCompress")
							+ targetName);
			try {
				destFile.createNewFile();
				FileOutputStream out = new FileOutputStream(
						CommonUtils.getIPConfigBykey("imagePathCompress")
								+ targetName);
				out.write(buffer);
				out.close();
				logger.info("【上传图片images2】压缩失败补救，复制图片成功,fileName=" + targetName);
			} catch (IOException ioe) {
				logger.error("【上传图片images2】压缩失败补救，复制原图片到压缩图片目录出现异常,fineName="
						+ targetName, ioe);
			}
		}

	}

	public static void doUploadOrder(byte[] buffer, String targetName)
			throws Exception {
		FileOutputStream out = new FileOutputStream(
				CommonUtils.getIPConfigBykey("imageOrderPath") + targetName);
		out.write(buffer);
		out.close();
	}

	public static void doBannerMediaUpload(byte[] buffer, String targetName)
			throws Exception {
		FileOutputStream out = new FileOutputStream(
				CommonUtils.getIPConfigBykey("bannerMediasPath") + targetName);
		out.write(buffer);
		out.close();
	}

	public static void doFlashUpload(byte[] buffer, String targetName)
			throws Exception {
		FileOutputStream out = new FileOutputStream(
				CommonUtils.getIPConfigBykey("flashPath") + targetName);
		out.write(buffer);
		out.close();
	}

	public static void main(String[] args) {
		// System.out.println(fileUpload(new File("D:/", "bak_5.jpg"),
		// "123.jpg"));

	}
}
