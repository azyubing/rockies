package com.rockies.common.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class CommonUtils {

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getLongTime() {
		long longTime = new Date().getTime();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return longTime + "";
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 分页
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> getPage(Map<String, Object> map) {
		Map<String, Object> pageMap = new HashMap<>(3);
		pageMap.put("pn", map.get("pn"));
		pageMap.put("ps", map.get("ps"));
		pageMap.put("total", map.get("total"));
		return pageMap;
	}

	/**
	 * String转MD5
	 * 
	 * @param plainText
	 * @return
	 */
	public static String covertpw(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return plainText;
	}

	/**
	 * 获取配置文件信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertiesBykey(String key) {
		String val = "";
		try {
			Resource resource = new ClassPathResource("/message.properties");
			Properties props;
			props = PropertiesLoaderUtils.loadProperties(resource);
			val = (String) props.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * 获取环境地址配置文件信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getIPConfigBykey(String key) {
		String val = "";
		try {
			Resource resource = new ClassPathResource("/ipconfig.properties");
			Properties props;
			props = PropertiesLoaderUtils.loadProperties(resource);
			val = (String) props.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * 获取接口配置信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getInterfaceConfigBykey(String key) {
		String val = "";
		try {
			Resource resource = new ClassPathResource("/interface-config.properties");
			Properties props;
			props = PropertiesLoaderUtils.loadProperties(resource);
			val = (String) props.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * 获取信息发送模板
	 * 
	 * @param key
	 * @return
	 */
	public static String getMsgTemplateBykey(String key) {
		String val = "";
		try {
			Resource resource = new ClassPathResource("/template-config.properties");
			// ① 指定文件资源对应的编码格式（UTF-8）
			EncodedResource encRes = new EncodedResource(resource, "UTF-8");
			Properties props = new Properties();
			props.load(encRes.getReader());
			val = (String) props.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * url-encode the param
	 * 
	 * @param param
	 * @return
	 */
	public static String escape(String param) {
		return escapers.escape(param);
	}

	private static final Escaper escapers = UrlEscapers.urlFormParameterEscaper();

	/**
	 * 生成二维码图片 不存储 直接以流的形式输出到页面
	 * 
	 * @param content
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, HttpServletResponse response) {
		if (StringUtils.isBlank(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static final int WHITE = 0xFFFFFFFF;
	private static final int BLACK = 0xFF000000;

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 获取2个时间之间的所有日期
	 * 
	 * @param start
	 * @param end
	 * @param calendarType
	 * @return
	 */
	public static List<Date> getDateArrays(Date start, Date end, int calendarType) {
		ArrayList<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(calendarType, 1);
			tmpDate = calendar.getTime();
		}

		return ret;
	}

	/**
	 * 获取指定日期月份的第一天的下几个月的第几天
	 * 
	 * @param date
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMonthFirst(String date, int month, int day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			String str = "";
			cal.add(Calendar.MONTH, month);
			cal.set(Calendar.DATE, day);
			str = sdf.format(cal.getTime());
			return str;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定日期月份的第一天的下几个月的最后一天
	 * 
	 * @param date
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMonthEnd(String date, int month) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(sdf.parse(date));
			lastDate.add(Calendar.MONTH, month);// 加一个月
			lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
			lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
			String str = sdf.format(lastDate.getTime());
			return str;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算2个日期相隔多少天
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long daysBetween(String beginDate, String endDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date smdate_ = sdf.parse(beginDate);
			Date bdate_ = sdf.parse(endDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate_);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate_);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return between_days + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long daysBetween(Date beginDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return between_days;
	}
}
