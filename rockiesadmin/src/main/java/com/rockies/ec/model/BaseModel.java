package com.rockies.ec.model;

import java.util.Date;

public class BaseModel {
    
	private String ctuser;
	private Date ctdt;
	private String upuser;
	private Date updt;
	
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
        this.ctuser = ctuser == null ? null : ctuser.trim();
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
        this.upuser = upuser == null ? null : upuser.trim();
    }




}
