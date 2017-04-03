package com.rockies.model;

import java.util.Date;

public class Collection {
    private Long cid;

    private String pid;

    private Integer userId;

    private String startplace;

    private String endplace;
    
    private int roomcnt;

    private int staylvl;
    
    private Date ctdt;

    private String ctuser;

    private Date updt;

    private String upuser;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStartplace() {
		return startplace;
	}

	public void setStartplace(String startplace) {
		this.startplace = startplace;
	}

	public String getEndplace() {
		return endplace;
	}

	public void setEndplace(String endplace) {
		this.endplace = endplace;
	}

	public int getRoomcnt() {
		return roomcnt;
	}

	public void setRoomcnt(int roomcnt) {
		this.roomcnt = roomcnt;
	}

	public int getStaylvl() {
		return staylvl;
	}

	public void setStaylvl(int staylvl) {
		this.staylvl = staylvl;
	}

	public Date getCtdt() {
		return ctdt;
	}

	public void setCtdt(Date ctdt) {
		this.ctdt = ctdt;
	}

	public String getCtuser() {
		return ctuser;
	}

	public void setCtuser(String ctuser) {
		this.ctuser = ctuser;
	}

	public Date getUpdt() {
		return updt;
	}

	public void setUpdt(Date updt) {
		this.updt = updt;
	}

	public String getUpuser() {
		return upuser;
	}

	public void setUpuser(String upuser) {
		this.upuser = upuser;
	}

    
}