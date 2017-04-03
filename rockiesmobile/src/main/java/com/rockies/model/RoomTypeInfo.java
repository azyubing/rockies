package com.rockies.model;


public class RoomTypeInfo extends RoomTypeInfoKey {
    
    private String roomTypeName;
    
    private String roomStatus;
    
    private String description;

    private Integer orderNum;

    private String roomImg1;

    private String roomImg2;

    private String roomImg3;

    private String roomImg4;

    private String roomImg5;

    private String roomImg6;

    private String roomImg7;

    private String roomInfra;

    private Integer adultCnt;

    private Integer childCnt;

    private Integer maxExtrabed;

    private String showInpage;

    
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRoomImg1() {
        return roomImg1;
    }

    public void setRoomImg1(String roomImg1) {
        this.roomImg1 = roomImg1 == null ? null : roomImg1.trim();
    }

    public String getRoomImg2() {
        return roomImg2;
    }

    public void setRoomImg2(String roomImg2) {
        this.roomImg2 = roomImg2 == null ? null : roomImg2.trim();
    }

    public String getRoomImg3() {
        return roomImg3;
    }

    public void setRoomImg3(String roomImg3) {
        this.roomImg3 = roomImg3 == null ? null : roomImg3.trim();
    }

    public String getRoomImg4() {
        return roomImg4;
    }

    public void setRoomImg4(String roomImg4) {
        this.roomImg4 = roomImg4 == null ? null : roomImg4.trim();
    }

    public String getRoomImg5() {
        return roomImg5;
    }

    public void setRoomImg5(String roomImg5) {
        this.roomImg5 = roomImg5 == null ? null : roomImg5.trim();
    }

    public String getRoomImg6() {
        return roomImg6;
    }

    public void setRoomImg6(String roomImg6) {
        this.roomImg6 = roomImg6 == null ? null : roomImg6.trim();
    }

    public String getRoomImg7() {
        return roomImg7;
    }

    public void setRoomImg7(String roomImg7) {
        this.roomImg7 = roomImg7 == null ? null : roomImg7.trim();
    }

    public String getRoomInfra() {
        return roomInfra;
    }

    public void setRoomInfra(String roomInfra) {
        this.roomInfra = roomInfra == null ? null : roomInfra.trim();
    }

    public Integer getAdultCnt() {
        return adultCnt;
    }

    public void setAdultCnt(Integer adultCnt) {
        this.adultCnt = adultCnt;
    }

    public Integer getChildCnt() {
        return childCnt;
    }

    public void setChildCnt(Integer childCnt) {
        this.childCnt = childCnt;
    }

    public Integer getMaxExtrabed() {
        return maxExtrabed;
    }

    public void setMaxExtrabed(Integer maxExtrabed) {
        this.maxExtrabed = maxExtrabed;
    }

    public String getShowInpage() {
        return showInpage;
    }

    public void setShowInpage(String showInpage) {
        this.showInpage = showInpage == null ? null : showInpage.trim();
    }

}