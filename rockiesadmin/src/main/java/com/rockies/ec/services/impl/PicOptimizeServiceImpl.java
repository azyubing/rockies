/**
 * 
 */
package com.rockies.ec.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.FileUpAndDown;
import com.rockies.ec.services.IpicOptimizeService;

/**
 * @author Administrator
 * 
 */
@Service
public class PicOptimizeServiceImpl implements IpicOptimizeService {

	private static final Logger logger = LoggerFactory
			.getLogger(PicOptimizeServiceImpl.class);

	private static final String JPG_SUFFIX = ".jpg";
	private static final String JPEG_SUFFIX = ".jpeg";
	private static final String PNG_SUFFIX = ".png";
	private static final String BMP_SUFFIX = ".bmp";
	private static final String GIF_SUFFIX = ".gif";

	/**
	 * 同步imagesPath目录下的所有图片，压缩到imagesPathCompress下面
	 * 
	 * @see com.rockies.ec.services.IpicOptimizeService#synchronizeCompressFiles()
	 */
	@Override
	public void synchronizeCompressFiles() {

		logger.info("【同步图片任务】:开始同步images目录下的图片，压缩到images2目录下");
		long currentMillis = System.currentTimeMillis();

		String imagesPath = CommonUtils.getIPConfigBykey("imagePath");
		String images2Path = CommonUtils.getIPConfigBykey("imagePathCompress");

		File imagesFolder = new File(imagesPath);
		File images2Folder = new File(images2Path);
		Set<String> images2FileNames = getFileNameSet(images2Folder.listFiles());

		File[] imagesFiles = imagesFolder.listFiles();
		for (File imagesFile : imagesFiles) {
			String imagesFileName = imagesFile.getName();
			try {

				if (!checkIsPicFile(imagesFileName)) {
					logger.info("【同步图片任务】不是图片文件,fileName=" + imagesFileName);
					continue;
				}

				if (!images2FileNames.contains(imagesFileName)) {
					logger.info("【同步图片任务】开始压缩图片,fileName="+imagesFileName);
					byte[] picBytes = readFileBytes(imagesFile);
					FileUpAndDown.compressAndSavePic(picBytes, imagesFileName);

				}
			} catch (Exception e) {
				logger.error("【同步图片任务】出现异常,fileName=" + imagesFileName, e);
				// 复制原图片
				File destFile = new File(
						CommonUtils.getIPConfigBykey("imagePathCompress")
								+ imagesFileName);
				try {
					destFile.createNewFile();
					FileUtils.copyFile(imagesFile, destFile);
					logger.info("【同步图片任务】复制图片成功,fileName=" + imagesFileName);
				} catch (IOException e1) {
					logger.error("【同步图片任务】复制原图片到压缩图片目录出现异常,fineName="
							+ imagesFileName, e);
				}
			}
		}

		logger.info("【同步图片任务】:任务结束,耗时:"+(System.currentTimeMillis()-currentMillis)+"毫秒");
	}

	/**
	 * 检查是否是图片文件
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkIsPicFile(String fileName) {
		String lowerCaseName = fileName.toLowerCase();
		return lowerCaseName.endsWith(JPG_SUFFIX)
				|| lowerCaseName.endsWith(JPEG_SUFFIX)
				|| lowerCaseName.endsWith(PNG_SUFFIX)
				|| lowerCaseName.endsWith(BMP_SUFFIX)
				|| lowerCaseName.endsWith(GIF_SUFFIX);

	}

	/**
	 * @param files
	 * @return
	 */
	private Set<String> getFileNameSet(File[] files) {
		Set<String> fileNameSets = new HashSet<String>();
		for (File file : files) {
			fileNameSets.add(file.getName());
		}
		return fileNameSets;
	}

	private byte[] readFileBytes(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		int length = fis.available();
		byte[] contents = new byte[length];
		fis.read(contents);
		fis.close();

		return contents;

	}
}
