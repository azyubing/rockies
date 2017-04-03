package com.rockies.ec.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo extends BaseModel {
    private String pid;

    private String ptype;

    private String pstatus;

    private String pnote;

    private String pname;

    private String pname_en;

    private String continent;

    private String country;

    private String city;

    private String supplier_no;

    private Date startdate;

    private Date enddate;

    private String list_img;

    private String size_img1;

    private String size_img2;

    private String size_img3 ;

    private String size_img4;

    private String size_img5;

    private BigDecimal start_price;

    private BigDecimal prepay;

    private String fromcity;

    private Long sale_cnt;

    private Long sharecnt;

    private Long collection_cnt;

    private String map_address;

    private String map_lng;

    private String map_lat;

    private String tags;

    private BigDecimal service_fee_rate;

    private BigDecimal tax_rate;

    private Integer confirm_time;
    
    private String del_flg;
    
    private String cityName;
    
    private String pdesc;
    
    private String price_desc;
    
    private String video_url;
    
	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPnote() {
        return pnote;
    }

    public void setPnote(String pnote) {
        this.pnote = pnote;
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

    public BigDecimal getStart_price() {
        return start_price;
    }

    public void setStart_price(BigDecimal start_price) {
        this.start_price = start_price;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public Long getSale_cnt() {
        return sale_cnt;
    }

    public void setSale_cnt(Long sale_cnt) {
        this.sale_cnt = sale_cnt;
    }

    public Long getSharecnt() {
        return sharecnt;
    }

    public void setSharecnt(Long sharecnt) {
        this.sharecnt = sharecnt;
    }

    public Long getCollection_cnt() {
        return collection_cnt;
    }

    public void setCollection_cnt(Long collection_cnt) {
        this.collection_cnt = collection_cnt;
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

    public BigDecimal getService_fee_rate() {
        return service_fee_rate;
    }

    public void setService_fee_rate(BigDecimal service_fee_rate) {
        this.service_fee_rate = service_fee_rate;
    }

    public BigDecimal getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(BigDecimal tax_rate) {
        this.tax_rate = tax_rate;
    }

    public Integer getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Integer confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getDel_flg() {
        return del_flg;
    }

    public void setDel_flg(String del_flg) {
        this.del_flg = del_flg;
    }

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getPrice_desc() {
		return price_desc;
	}

	public void setPrice_desc(String price_desc) {
		this.price_desc = price_desc;
	}
}