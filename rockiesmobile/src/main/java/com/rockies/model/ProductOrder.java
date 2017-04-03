package com.rockies.model;

import java.util.Date;

/**
 * 产品订单
 * @author hyh
 *
 */
public class ProductOrder extends BaseModel {
	private String orderNo;//行程单编号
	private long runingNo;//流水号
	private String pid;//产品ID
	private Date inday;//行程日期
	private String attime;//时间
	private String ptype;//产品类别 : 产品分类：套餐，酒店，别墅，交通，娱乐
	private String pname;//产品名称
	private String pname_en;//名称英文
	private String continent;//所属大洲
	private String country;//国家
	private String city;//城市
	private String supplier_no;//供货商编码
	private Date startdate;//有效时间开始
	private Date enddate;//有效时间结束
	private String topic;//所属主题 : 如：蜜月，婚礼，亲子
	private String list_img;//列表展示图片
	private String size_img1;//图片规格1
	private String size_img2;//图片规格2
	private String size_img3;//图片规格3
	private String size_img4;//图片规格4
	private String size_img5;//图片规格5
	private String start_price;//起点价格
	private String prepay;//预付款
	private String delFlg;//有效FLG  默认0
	private String fromcity;//出发城市
	private String map_address;//地图地址
	private String map_lng;//地图经度
	private String map_lat;//地图纬度
	private String tags;//标签
	private String confirmStatus;//确认状态  0:已确认 1：未确认
	private String confirmImg;//确认凭证图片
	private int confirm_time;//确认时间（小时） : 服务确认时间： 0:即使确认12:12小时确认48：24小时确认
	private String traffic_type;//交通类型
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public long getRuningNo() {
		return runingNo;
	}
	public void setRuningNo(long runingNo) {
		this.runingNo = runingNo;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getInday() {
		return inday;
	}
	public void setInday(Date inday) {
		this.inday = inday;
	}
	public String getAttime() {
		return attime;
	}
	public void setAttime(String attime) {
		this.attime = attime;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPname_en() {
		return pname_en;
	}
	public void setPname_en(String pname_en) {
		this.pname_en = pname_en;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSupplier_no() {
		return supplier_no;
	}
	public void setSupplier_no(String supplier_no) {
		this.supplier_no = supplier_no;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	public String getSize_img1() {
		return size_img1;
	}
	public void setSize_img1(String size_img1) {
		this.size_img1 = size_img1;
	}
	public String getSize_img2() {
		return size_img2;
	}
	public void setSize_img2(String size_img2) {
		this.size_img2 = size_img2;
	}
	public String getSize_img3() {
		return size_img3;
	}
	public void setSize_img3(String size_img3) {
		this.size_img3 = size_img3;
	}
	public String getSize_img4() {
		return size_img4;
	}
	public void setSize_img4(String size_img4) {
		this.size_img4 = size_img4;
	}
	public String getSize_img5() {
		return size_img5;
	}
	public void setSize_img5(String size_img5) {
		this.size_img5 = size_img5;
	}
	public String getStart_price() {
		return start_price;
	}
	public void setStart_price(String start_price) {
		this.start_price = start_price;
	}
	public String getPrepay() {
		return prepay;
	}
	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getFromcity() {
		return fromcity;
	}
	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}
	public String getMap_address() {
		return map_address;
	}
	public void setMap_address(String map_address) {
		this.map_address = map_address;
	}
	public String getMap_lng() {
		return map_lng;
	}
	public void setMap_lng(String map_lng) {
		this.map_lng = map_lng;
	}
	public String getMap_lat() {
		return map_lat;
	}
	public void setMap_lat(String map_lat) {
		this.map_lat = map_lat;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getConfirmImg() {
		return confirmImg;
	}
	public void setConfirmImg(String confirmImg) {
		this.confirmImg = confirmImg;
	}
	public int getConfirm_time() {
		return confirm_time;
	}
	public void setConfirm_time(int confirm_time) {
		this.confirm_time = confirm_time;
	}
	public String getTraffic_type() {
		return traffic_type;
	}
	public void setTraffic_type(String traffic_type) {
		this.traffic_type = traffic_type;
	}
	@Override
	public String toString() {
		return "ProductOrder [orderNo=" + orderNo + ", runingNo=" + runingNo
				+ ", pid=" + pid + ", inday=" + inday + ", attime=" + attime
				+ ", ptype=" + ptype + ", pname=" + pname + ", pname_en="
				+ pname_en + ", continent=" + continent + ", country="
				+ country + ", city=" + city + ", supplier_no=" + supplier_no
				+ ", startdate=" + startdate + ", enddate=" + enddate
				+ ", topic=" + topic + ", list_img=" + list_img
				+ ", size_img1=" + size_img1 + ", size_img2=" + size_img2
				+ ", size_img3=" + size_img3 + ", size_img4=" + size_img4
				+ ", size_img5=" + size_img5 + ", start_price=" + start_price
				+ ", prepay=" + prepay + ", delFlg=" + delFlg + ", fromcity="
				+ fromcity + ", map_address=" + map_address + ", map_lng="
				+ map_lng + ", map_lat=" + map_lat + ", tags=" + tags
				+ ", confirmStatus=" + confirmStatus + ", confirmImg="
				+ confirmImg + ", confirm_time=" + confirm_time
				+ ", traffic_type=" + traffic_type + "]";
	}
	
	
}
