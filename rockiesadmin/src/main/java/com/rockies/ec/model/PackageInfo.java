package com.rockies.ec.model;


public class PackageInfo extends ProductInfo {
    private String pid;

    private String topic;

    private String sub_topic;

    private Integer daycnt;

    private Integer staylvl;

    private String property;

    private Integer peoplecnt;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getSub_topic() {
        return sub_topic;
    }

    public void setSub_topic(String sub_topic) {
        this.sub_topic = sub_topic;
    }

    public Integer getDaycnt() {
        return daycnt;
    }

    public void setDaycnt(Integer daycnt) {
        this.daycnt = daycnt;
    }

    public Integer getStaylvl() {
        return staylvl;
    }

    public void setStaylvl(Integer staylvl) {
        this.staylvl = staylvl;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public Integer getPeoplecnt() {
        return peoplecnt;
    }

    public void setPeoplecnt(Integer peoplecnt) {
        this.peoplecnt = peoplecnt;
    }

}