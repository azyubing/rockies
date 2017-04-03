package com.rockies.ec.model;

import java.util.Date;

/**
 * 产品订单
 * @author hyh
 *
 */
public class ProductOrder extends BaseModel {
	private String orderNo;//行程单编号
	private String runingNo;//流水号
	private String pid;//产品ID
	private Date inday;//行程日期
	private String attime;//时间
	private String ptype;//产品类别 : 产品分类：套餐，酒店，别墅，交通，娱乐
	private String pname;//产品名称
	private String pnameEn;//名称英文
	private String continent;//所属大洲
	private String country;//国家
	private String city;//城市
	private String supplierNo;//供货商编码
	private Date startdate;//有效时间开始
	private Date enddate;//有效时间结束
	private String topic;//所属主题 : 如：蜜月，婚礼，亲子
	private String listImg;//列表展示图片
	private String sizeImg1;//图片规格1
	private String sizeImg2;//图片规格2
	private String sizeImg3;//图片规格3
	private String sizeImg4;//图片规格4
	private String sizeImg5;//图片规格5
	private String startPrice;//起点价格
	private String prepay;//预付款
	private String delFlg;//有效FLG  默认0
	private String fromcity;//出发城市
	private String mapAddress;//地图地址
	private String mapLng;//地图经度
	private String mapLat;//地图纬度
	private String tags;//标签
	private String confirmStatus;//确认状态  0:已确认 1：未确认
	private String confirmImg;//确认凭证图片
	private int confirmTime;//确认时间（小时） : 服务确认时间： 0:即使确认12:12小时确认48：24小时确认
	private String payImg;//付款凭证
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRuningNo() {
		return runingNo;
	}
	public void setRuningNo(String runingNo) {
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
	public String getPnameEn() {
		return pnameEn;
	}
	public void setPnameEn(String pnameEn) {
		this.pnameEn = pnameEn;
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
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
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
	public String getListImg() {
		return listImg;
	}
	public void setListImg(String listImg) {
		this.listImg = listImg;
	}
	public String getSizeImg1() {
		return sizeImg1;
	}
	public void setSizeImg1(String sizeImg1) {
		this.sizeImg1 = sizeImg1;
	}
	public String getSizeImg2() {
		return sizeImg2;
	}
	public void setSizeImg2(String sizeImg2) {
		this.sizeImg2 = sizeImg2;
	}
	public String getSizeImg3() {
		return sizeImg3;
	}
	public void setSizeImg3(String sizeImg3) {
		this.sizeImg3 = sizeImg3;
	}
	public String getSizeImg4() {
		return sizeImg4;
	}
	public void setSizeImg4(String sizeImg4) {
		this.sizeImg4 = sizeImg4;
	}
	public String getSizeImg5() {
		return sizeImg5;
	}
	public void setSizeImg5(String sizeImg5) {
		this.sizeImg5 = sizeImg5;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
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
	public String getMapAddress() {
		return mapAddress;
	}
	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}
	public String getMapLng() {
		return mapLng;
	}
	public void setMapLng(String mapLng) {
		this.mapLng = mapLng;
	}
	public String getMapLat() {
		return mapLat;
	}
	public void setMapLat(String mapLat) {
		this.mapLat = mapLat;
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
	public int getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(int confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getPayImg() {
		return payImg;
	}
	public void setPayImg(String payImg) {
		this.payImg = payImg;
	}
	@Override
	public String toString() {
		return "ProductOrder [orderNo=" + orderNo + ", runingNo=" + runingNo
				+ ", pid=" + pid + ", inday=" + inday + ", attime=" + attime
				+ ", ptype=" + ptype + ", pname=" + pname + ", pnameEn="
				+ pnameEn + ", continent=" + continent + ", country=" + country
				+ ", city=" + city + ", supplierNo=" + supplierNo
				+ ", startdate=" + startdate + ", enddate=" + enddate
				+ ", topic=" + topic + ", listImg=" + listImg + ", sizeImg1="
				+ sizeImg1 + ", sizeImg2=" + sizeImg2 + ", sizeImg3="
				+ sizeImg3 + ", sizeImg4=" + sizeImg4 + ", sizeImg5="
				+ sizeImg5 + ", startPrice=" + startPrice + ", prepay="
				+ prepay + ", delFlg=" + delFlg + ", fromcity=" + fromcity
				+ ", mapAddress=" + mapAddress + ", mapLng=" + mapLng
				+ ", mapLat=" + mapLat + ", tags=" + tags + ", confirmStatus="
				+ confirmStatus + ", confirmImg=" + confirmImg
				+ ", confirmTime=" + confirmTime + ", payImg=" + payImg + "]";
	}
	

}
