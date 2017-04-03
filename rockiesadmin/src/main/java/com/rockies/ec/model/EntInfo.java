package com.rockies.ec.model;


public class EntInfo extends ProductInfo {
    private String enttype;

    private String servicetype;

    private String language;

    private String ent_img;

    private String show_img1;

    private String show_img2;

    private String show_img3;

    private String show_img4;


    public String getEnttype() {
        return enttype;
    }

    public void setEnttype(String enttype) {
        this.enttype = enttype == null ? null : enttype.trim();
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype == null ? null : servicetype.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getEnt_img() {
        return ent_img;
    }

    public void setEnt_img(String ent_img) {
        this.ent_img = ent_img;
    }

    public String getShow_img1() {
        return show_img1;
    }

    public void setShow_img1(String show_img1) {
        this.show_img1 = show_img1;
    }

    public String getShow_img2() {
        return show_img2;
    }

    public void setShow_img2(String show_img2) {
        this.show_img2 = show_img2;
    }

    public String getShow_img3() {
        return show_img3;
    }

    public void setShow_img3(String show_img3) {
        this.show_img3 = show_img3;
    }

    public String getShow_img4() {
        return show_img4;
    }

    public void setShow_img4(String show_img4) {
        this.show_img4 = show_img4;
    }

    

    
}