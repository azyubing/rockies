package com.rockies.ec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.rockies.ec.common.utils.CommonUtils;
import com.rockies.ec.common.utils.JSONProxy;
import com.rockies.ec.model.City;
import com.rockies.ec.model.HotelInfo;
import com.rockies.ec.model.RoomPriceInfo;
import com.rockies.ec.model.RoomTypeInfo;
import com.rockies.ec.model.SuppliersBean;
import com.rockies.ec.services.ICityService;
import com.rockies.ec.services.IHotelInfoService;
import com.rockies.ec.services.IRoomPriceInfoService;
import com.rockies.ec.services.IRoomTypeInfoService;
import com.rockies.ec.services.ISuppliersService;


@Controller
public class HotelController extends BaseController{

    @Autowired private IHotelInfoService hotelInfoService;
    @Autowired private ISuppliersService suppliersService;
    @Autowired private IRoomTypeInfoService roomTypeInfoService;
    @Autowired private IRoomPriceInfoService roomPriceInfoService;
    @Autowired private ICityService cityService;
    
    /**
     * hotel管理
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/hotelAdmin",method=RequestMethod.GET)
    public ModelAndView hotelAdmin(HttpServletResponse response, HttpServletRequest request,
            String msg,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/hotelAdmin","menu",menu);
        try {
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".hotelAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/hotel/hotelAdminSearchList")
    public void hotelAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String pname, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pname", pname);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = hotelInfoService.getCount(param);
            List<HotelInfo> rows = hotelInfoService.selectAllListByParam(param);
            for(HotelInfo row:rows){
            	row.setPdesc("");
            }
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".hotelAdminSearchList " + e.toString());
        }
    }
    
    /**
     * 跳转hotel增加页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toAddHotelAdmin",method=RequestMethod.GET)
    public ModelAndView toAddHotelAdmin(HttpServletResponse response, HttpServletRequest request,
            String menu) {
        ModelAndView mav = new ModelAndView("/hotel/addHotel","menu",menu);
        try {

            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);
            
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("row",new HotelInfo());
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype1"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode1"));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddHotelAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 新增酒店
     * @param response
     * @param request
     * @param menu
     * @param productInfo
     * @param hotelInfo
     * @return
     */
    @RequestMapping(value = "/hotel/addHotelAdmin",method=RequestMethod.POST)
    public ModelAndView addHotelAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            HotelInfo hotelInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/hotel/hotelAdmin.html","menu",menu);
        try {
            String userName = "test1";
            hotelInfo.setCtdt(new Date());
            hotelInfo.setCtuser(userName);
            if(hotelInfoService.insert(hotelInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addHotelAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 跳转酒店修改页面
     * @param response
     * @param request
     * @param pid
     * @param menu
     * @return
     */
    @RequestMapping(value = "/hotel/toUpdateHotelAdmin")
    public ModelAndView toUpdateHotelAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid, String menu) {
        ModelAndView mav = new ModelAndView("/hotel/updateHotel","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            HotelInfo row = hotelInfoService.selectHotelInfoByParam(param);
            mav.addObject("row",row);
            
            List<SuppliersBean> suppList = suppliersService.getAllList();
            mav.addObject("suppList",suppList);
            
            Map<String,Object> paramCity = new HashMap<>(1);
            paramCity.put("parentId", 0);
            List<City> cityList = cityService.getCityList(paramCity);
            mav.addObject("cityList", cityList);
            
            mav.addObject("pTypeName",CommonUtils.getPropertiesBykey("ptype1"));
            mav.addObject("pTypeCode",CommonUtils.getPropertiesBykey("ptypeCode1"));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateHotelAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 酒店修改
     * @param response
     * @param request
     * @param menu
     * @param hotelInfo
     * @param pid
     * @return
     */
    @RequestMapping(value = "/hotel/updateHotelAdmin",method=RequestMethod.POST)
    public ModelAndView updateHotelAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            HotelInfo hotelInfo) {

        ModelAndView mav = new ModelAndView("redirect:/hotel/hotelAdmin.html","menu",menu);
        try {
            String userName = "test2";
            hotelInfo.setUpdt(new Date());
            hotelInfo.setUpuser(userName);
            if(hotelInfoService.updateByPrimaryKeySelective(hotelInfo)){
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addHotelAdmin " + e.toString());

        }
        return mav;
    }
    
    /**
     * 酒店删除
     * @param response
     * @param request
     * @param menu
     * @param hotelInfo
     */
    @RequestMapping(value = "/hotel/deleteHotelAdmin",method=RequestMethod.POST)
    public void deleteHotelAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            HotelInfo hotelInfo) {
        try {
            hotelInfo.setDel_flg("1");
            if(hotelInfoService.updateByPrimaryKeySelective(hotelInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addHotelAdmin " + e.toString());

        }
    }
    
    /**
     * 跳转hotel标签增加页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/hotelTagAdmin",method=RequestMethod.GET)
    public ModelAndView hotelTagAdmin(HttpServletResponse response, HttpServletRequest request,
    		String menu) {
    	try {
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.error("login fail."+this.getClass()+".hotelTagAdmin " + e.toString());
    	}
    	return new ModelAndView("/hotel/hotelTagAdmin","menu",menu);
    }
    /**
     * 跳转hotel房型设定页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toSetRoomAdmin",method=RequestMethod.GET)
    public ModelAndView toSetRoomAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toSetRoomAdmin","menu",menu);
    	try {
    	    
    	    if(msg != null){
                mav.addObject("msg",msg);
            }
    	    
    	    Map<String,Object> param = new HashMap<>(3);
            param.put("pid", pid);
            
            HotelInfo row = hotelInfoService.selectHotelInfoByParam(param);
            mav.addObject("row",row);
            
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.error("login fail."+this.getClass()+".toSetRoomAdmin " + e.toString());
    	}
    	return mav;
    }
    @RequestMapping(value = "/hotel/setRoomAdminSearchList")
    public void setRoomAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String roomTypeName,String pid, Integer offset, Integer limit) {
        
//        int ps = getPs(offset, limit);
//        int pn = getPn(offset, limit);
        
        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("roomTypeName", roomTypeName);
            param.put("pid", pid);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = roomTypeInfoService.getCount(param);
            List<RoomTypeInfo> rows = roomTypeInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".setRoomAdminSearchList " + e.toString());
        }
    }
    
    /**
     * 跳转hotel房型增加页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toAddRoomAdmin",method=RequestMethod.GET)
    public ModelAndView toAddRoomAdmin(HttpServletResponse response, HttpServletRequest request,
    		String pid,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toAddRoomAdmin","menu",menu);
    	try {
    	    mav.addObject("pid",pid);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.error("login fail."+this.getClass()+".toAddRoomAdmin " + e.toString());
    	}
    	return mav;
    }

    
    /**
     * 房型新增
     * @param response
     * @param request
     * @param menu
     * @param roomTypeInfo
     * @return
     */
    @RequestMapping(value = "/hotel/addRoomAdmin",method=RequestMethod.POST)
    public ModelAndView addRoomAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomTypeInfo roomTypeInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/hotel/toSetRoomAdmin.html","menu",menu);
        mav.addObject("pid",roomTypeInfo.getPid());
        try {
            String userName = "test1";
            roomTypeInfo.setCtdt(new Date());
            roomTypeInfo.setCtuser(userName);
            if(roomTypeInfoService.insert(roomTypeInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addRoomAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 跳转hotel房型修改页面
     * @param response
     * @param request
     * @param pid
     * @param roomType
     * @param menu
     * @return
     */
    @RequestMapping(value = "/hotel/toUpdateRoomAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateRoomAdmin(HttpServletResponse response, HttpServletRequest request,
            RoomTypeInfo roomTypeInfo, String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toUpdateRoomAdmin","menu",menu);
        try {
            mav.addObject("row",roomTypeInfoService.selectByPrimaryKey(roomTypeInfo));
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateRoomAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 房型修改
     * @param response
     * @param request
     * @param menu
     * @param roomTypeInfo
     * @return
     */
    @RequestMapping(value = "/hotel/updateRoomAdmin",method=RequestMethod.POST)
    public ModelAndView updateRoomAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomTypeInfo roomTypeInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/hotel/toSetRoomAdmin.html","menu",menu);
        mav.addObject("pid",roomTypeInfo.getPid());
        try {
            String userName = "test2";
            roomTypeInfo.setCtdt(new Date());
            roomTypeInfo.setCtuser(userName);
            if(roomTypeInfoService.updateByPrimaryKeySelective(roomTypeInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateRoomAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 房型删除
     * @param response
     * @param request
     * @param menu
     * @param roomTypeInfo
     */
    @RequestMapping(value = "/hotel/deleteRoomAdmin",method=RequestMethod.POST)
    public void deleteRoomAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomTypeInfo roomTypeInfo) {
        try {
            roomTypeInfo.setDelFlg("1");
            if(roomTypeInfoService.updateByPrimaryKeySelective(roomTypeInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteRoomAdmin " + e.toString());

        }
    }
    
    /**
     * 跳转hotel房型价格设定页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toSetRoomPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toSetRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            RoomTypeInfo roomTypeInfo,String msg,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toSetRoomPriceAdmin","menu",menu);
        try {
            
            if(msg != null){
                mav.addObject("msg",msg);
            }
            
            RoomTypeInfo row = roomTypeInfoService.selectByPrimaryKey(roomTypeInfo);
            mav.addObject("row",row);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toSetRoomPriceAdmin " + e.toString());
        }
        return mav;
    }
    @RequestMapping(value = "/hotel/setRoomPriceAdminSearchList")
    public void setRoomPriceAdminSearchList(HttpServletResponse response, HttpServletRequest request,
            String priceItem,String pid,String roomType, Integer offset, Integer limit) {

        try {
            Map<String,Object> param = new HashMap<>(3);
            param.put("priceItem", priceItem);
            param.put("pid", pid);
            param.put("roomType", roomType);
            param.put("offset", offset);
            param.put("limit", limit);
            int total = roomTypeInfoService.getCount(param);
            List<RoomPriceInfo> rows = roomPriceInfoService.selectAllListByParam(param);
            
            Map<String, Object> reslutMap = new HashMap<>();
            reslutMap.put("rows", rows);
            reslutMap.put("total", total);
            response.getWriter().print(JSON.toJSONString(reslutMap, JSONProxy.SYSTEM_FEATURE));

        } catch (Exception e) {
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".setRoomPriceAdminSearchList " + e.toString());
        }
    }
    
    /**
     * 跳转hotel房型价格增加页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toAddRoomPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toAddRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            String pid,String roomType,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toAddRoomPriceAdmin","menu",menu);
        try {
            Map<String,Object> param = new HashMap<>(2);
            param.put("pid", pid);
            
            HotelInfo hotelInfo = hotelInfoService.selectHotelInfoByParam(param);
            mav.addObject("hotelInfo",hotelInfo);
            
            param.put("roomType", roomType);
            Map<String,Object> row = roomTypeInfoService.selectRoomInfoByParam(param);
            mav.addObject("row",row);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toAddRoomPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 房型价格新增
     * @param response
     * @param request
     * @param menu
     * @param roomPriceInfo
     * @return
     */
    @RequestMapping(value = "/hotel/addRoomPriceAdmin",method=RequestMethod.POST)
    public ModelAndView addRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomPriceInfo roomPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/hotel/toSetRoomPriceAdmin.html","menu",menu);
        mav.addObject("pid",roomPriceInfo.getPid());
        mav.addObject("roomType",roomPriceInfo.getRoomType());
        try {
            String userName = "test1";
            roomPriceInfo.setCtdt(new Date());
            roomPriceInfo.setCtuser(userName);
            roomPriceInfo.setDelFlg("0");
            if(roomPriceInfoService.insert(roomPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".addRoomPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 跳转hotel房型价格修改页面
     * @param response
     * @param request
     * @param roomPriceInfo
     * @param menu
     * @return
     */
    @RequestMapping(value = "/hotel/toUpdateRoomPriceAdmin",method=RequestMethod.GET)
    public ModelAndView toUpdateRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request,
            RoomPriceInfo roomPriceInfo,String menu) {
        ModelAndView mav = new ModelAndView("/hotel/toUpdateRoomPriceAdmin","menu",menu);
        try {

            Map<String,Object> param = new HashMap<>(2);
            param.put("pid", roomPriceInfo.getPid());
            
            HotelInfo hotelInfo = hotelInfoService.selectHotelInfoByParam(param);
            mav.addObject("hotelInfo",hotelInfo);
            
            param.put("roomType", roomPriceInfo.getRoomType());
            Map<String,Object> hotelRoomInfo = roomTypeInfoService.selectRoomInfoByParam(param);
            mav.addObject("hotelRoomInfo",hotelRoomInfo);
            
            mav.addObject("row",roomPriceInfoService.selectByPrimaryKey(roomPriceInfo));
            
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".toUpdateRoomPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * hotel房型价格修改
     * @param response
     * @param request
     * @param menu
     * @param roomPriceInfo
     * @return
     */
    @RequestMapping(value = "/hotel/updateRoomPriceAdmin",method=RequestMethod.POST)
    public ModelAndView updateRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomPriceInfo roomPriceInfo) {
        
        ModelAndView mav = new ModelAndView("redirect:/hotel/toSetRoomPriceAdmin.html","menu",menu);
        mav.addObject("pid",roomPriceInfo.getPid());
        mav.addObject("roomType",roomPriceInfo.getRoomType());
        try {
            String userName = "test1";
            roomPriceInfo.setCtdt(new Date());
            roomPriceInfo.setCtuser(userName);
            roomPriceInfo.setDelFlg("0");
            if(roomPriceInfoService.updateByPrimaryKeySelective(roomPriceInfo)){
                
                mav.addObject("msg","保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".updateRoomPriceAdmin " + e.toString());
        }
        return mav;
    }
    
    /**
     * 房型价格删除
     * @param response
     * @param request
     * @param menu
     * @param roomPriceInfo
     */
    @RequestMapping(value = "/hotel/deleteRoomPriceAdmin",method=RequestMethod.POST)
    public void deleteRoomPriceAdmin(HttpServletResponse response, HttpServletRequest request, String menu,
            RoomPriceInfo roomPriceInfo) {
        try {
            roomPriceInfo.setDelFlg("1");
            if(roomPriceInfoService.deleteByPrimaryKey(roomPriceInfo)){
                response.getWriter().print(JSON.toJSONString("修改成功", JSONProxy.SYSTEM_FEATURE));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != e.getMessage()) {
                System.out.println(e.toString());
            }
            logger.error("login fail."+this.getClass()+".deleteRoomAdmin " + e.toString());

        }
    }
    
    /**
     * 跳转价格日历页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotel/toEditPriceAndCalender",method=RequestMethod.GET)
    public ModelAndView toEditPriceAndCalender(HttpServletResponse response, HttpServletRequest request,
    		String menu) {
    	try {
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (null != e.getMessage()) {
    			System.out.println(e.toString());
    		}
    		logger.error("login fail."+this.getClass()+".hotelRoomAdmin " + e.toString());
    	}
    	return new ModelAndView("/hotel/toEditPriceAndCalender","menu",menu);
    }
}
