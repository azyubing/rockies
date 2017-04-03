package com.rockies.ec.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;


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
     * 套餐行程详细类型配置文件信息
     * 
     * @param key
     * @return
     */
    public static String getPropertiesBykey(String key) {
        String val = "";
        try {
            Resource resource = new ClassPathResource("/itype-img.properties");
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
     * 获取发件人信息
     * 
     * @param key
     * @return
     */
    public static String getPropertiesBySendEmail(String key) {
        String val = "";
        try {
            Resource resource = new ClassPathResource("/sendEmailInfo.properties");
            Properties props;
            props = PropertiesLoaderUtils.loadProperties(resource);
            val = (String) props.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }

    /**
     * 获得接口数据
     * 
     * @param URL
     * @deprecated 请使用 {@code get} {@code post}
     * @return
     *
     */
    public static String getResultStr(String URL) {
        try {
            return getHtmlStr(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转化为DateString
     * 
     * @param timeStamp
     * @return
     * @throws ParseException
     */
    public static String getTimeStampToDateString(long timeStamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timeStamp);
    }

    private static String getHtmlStr(String urlstr) throws Exception {
        String content = "";
        URL url = new URL(urlstr);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.connect();
        content = read(httpCon.getInputStream(), "utf-8");
        if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return content;
        }
        return "";
    }

    private static String read(InputStream input, String encode) {
        BufferedReader buffer = null;
        try {
            byte[] data = readInputStream(input); // 把二进制数据转化为byte字节数据
            String htmlSource = new String(data, encode);
            return htmlSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static byte[] readInputStream(InputStream instream) throws Exception {
        java.io.ByteArrayOutputStream outStream = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        instream.close();
        return outStream.toByteArray();
    }

    /**
     * extra the api response message
     * 
     * @param apiResult the api response message
     * @param returnData return the data if api response success, otherwise the error message<br/>
     *        the error message is json format: {"__sp__error_tag": "error message"}
     * @return true if api response success, otherwise false
     */
    public static boolean extraApiResult(String apiResult, StringBuilder returnData) {
        ApiResponse apiResponse = JSON.parseObject(apiResult, ApiResponse.class);
        int err = apiResponse.getErr();
        if (err == 0) {
            String data = apiResponse.getData();
            returnData.append(data == null ? "{}" : data);
            return true;
        }
        returnData.append(String.format("{\"__sp__error_tag\":\"%s\"}",
                ErrorUtil.getErrorMsg(err, apiResponse.getMsg())));
        return false;
    }

    public static String extraApiResult(String apiResult) {
        StringBuilder data = new StringBuilder();
        extraApiResult(apiResult, data);
        return data.toString();
    }

    public static String post(String url, Map<String, ?> params) {
        return new HttpRequest(url, HttpRequest.METHOD_POST).form(params).body();
    }

    public static String get(String url, Map<String, ?> params) {
        return new HttpRequest(url, HttpRequest.METHOD_GET).form(params).body();
    }

    public static String get(String url) {
        return new HttpRequest(url, HttpRequest.METHOD_GET).body();
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
            EncodedResource encRes = new EncodedResource(resource,"UTF-8"); 
            Properties props =  new  Properties();        
            props.load(encRes.getReader());   
            val = (String) props.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }
    
}
