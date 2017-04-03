package com.rockies.model;

import java.util.Date;

/**
 * Created by MyWay on 2016/9/26.
 */
public class MessageHistory {

    private Long id;

    private String type;

    private String from;

    private String to;

    private String message;

    private Date sendTime;

    public MessageHistory() {
    }

    public MessageHistory(String type, String from, String to, String message, Date sendTime) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.message = message;
        this.sendTime = sendTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
