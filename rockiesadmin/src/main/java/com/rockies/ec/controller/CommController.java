package com.rockies.ec.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.FileUpAndDown;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.services.ICommonService;


@Controller
@SuppressWarnings({"rawtypes","unchecked"})
public class CommController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CommController.class);
    
    @Autowired
	private ICommonService commonService;
    
    @RequestMapping(value = "/img/uploadFileList",method=RequestMethod.POST)
    public void uploadFileList(HttpServletResponse response, HttpServletRequest request,
    		RedirectAttributes attr, @RequestParam("uploadList") MultipartFile[] fileList) {
    	
    	boolean flg = true;
    	String msg = "";
    	Map<String,Object> resMap = new HashMap<>();
    	List<String> imgUrlList = new ArrayList(1);
    	for (int i = 0; i < fileList.length; i++) {
    		msg = checkFile(fileList[i]);
    		if(null == msg || !"Y".equals(msg)){		
    			attr.addFlashAttribute("msg", msg);	
    			logger.error(msg);
    			resMap.put("errcode", 1);
    			resMap.put("errmsg", msg);
    			flg = false;
    			break;
    		}
    	}
    	if(flg){
    		for (int i = 0; i < fileList.length; i++) {
    			String resString = FileUpAndDown.fileUpload(fileList[i], fileList[i].getOriginalFilename());
    			imgUrlList.add(resString);
    		}
    		resMap.put("errcode", 0);
    		resMap.put("data", imgUrlList);
    	}
    	try {
    		response.getWriter().print(JSON.toJSONString(resMap, JSONProxy.SYSTEM_FEATURE));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	@RequestMapping(value = "/img/uploadOrderFileList")
	public void uploadOrderFileList(HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes attr, @RequestParam("uploadList") MultipartFile[] fileList) {
		
		boolean flg = true;
    	String msg = "";
    	Map<String,Object> resMap = new HashMap<>();
    	List<String> imgUrlList = new ArrayList(1);
    	for (int i = 0; i < fileList.length; i++) {
    		msg = checkFile(fileList[i]);
    		if(null == msg || !"Y".equals(msg)){		
    			attr.addFlashAttribute("msg", msg);	
    			logger.error(msg);
    			resMap.put("errcode", 1);
    			resMap.put("errmsg", msg);
    			flg = false;
    			break;
    		}
    	}
    	if(flg){
    		for (int i = 0; i < fileList.length; i++) {
    			String resString = FileUpAndDown.uploadOrderFile(fileList[i], fileList[i].getOriginalFilename());
    			imgUrlList.add(resString);
    		}
    		resMap.put("errcode", 0);
    		resMap.put("data", imgUrlList);
    	}
    	try {
    		response.getWriter().print(JSON.toJSONString(resMap, JSONProxy.SYSTEM_FEATURE));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	// banner 视频 上传
	@RequestMapping(value = "/bannerMedia/uploadbannerMediaFileList")
	public void uploadbannerMediaFileList(HttpServletResponse response,
			HttpServletRequest request, RedirectAttributes attr,
			@RequestParam("webmMedia") MultipartFile[] webmMedia,
			@RequestParam("ogvMedia") MultipartFile[] ogvMedia,
			@RequestParam("mp4Media") MultipartFile[] mp4Media) {
		List<String> urlList = new ArrayList(1);
		String resStringwebmMedia = FileUpAndDown.bannerMediafileUpload(webmMedia[0], webmMedia[0].getOriginalFilename());
		String resStringogvMedia = FileUpAndDown.bannerMediafileUpload(ogvMedia[0], ogvMedia[0].getOriginalFilename());
		String resStringmp4Media = FileUpAndDown.bannerMediafileUpload(mp4Media[0], mp4Media[0].getOriginalFilename());
		urlList.add(resStringwebmMedia);
		urlList.add(resStringogvMedia);
		urlList.add(resStringmp4Media);
		try {
			response.getWriter().print(JSON.toJSONString(urlList, JSONProxy.SYSTEM_FEATURE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String checkFile(MultipartFile file){
		try{
			String fileName = file.getOriginalFilename();
			String message="Y";
			int imageMaxSize = Integer.valueOf(CommonUtils.getIPConfigBykey("imageMaxSize"));
			 if(file == null || file.isEmpty() ){
	            	message="对不起,你上传的文件不存在!!!";
	            }
	            if(!fileName.toLowerCase().endsWith(".jpg")
	            		&&!fileName.toLowerCase().endsWith(".png")
	            		&&!fileName.toLowerCase().endsWith(".jpeg")
	            		){
	                message="对不起,你上传的文件格式不允许!!!";
	            }  
	            if(file.getSize()>imageMaxSize)
	            {   
	            	 message="上传图片超过最大上传大小2M,请调整后重新上传。"; 
	            }
	            return message;
		}catch(Exception e){
			logger.error(e.toString());
			return "操作失败！ 原因："+e.toString();
		}
	}
	
	@RequestMapping(value = "/autoCompleteList")
	public void autoCompleteList(HttpServletResponse response, HttpServletRequest request,String tblnm,String colnm) {
		List<String> retList = new ArrayList(1);
		retList = commonService.getAutoCompleteList(tblnm, colnm);
		
		try {
            response.getWriter().print(JSON.toJSONString(retList, JSONProxy.SYSTEM_FEATURE));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
