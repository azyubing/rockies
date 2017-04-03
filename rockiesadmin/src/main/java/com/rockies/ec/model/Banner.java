package com.rockies.ec.model;

public class Banner extends BaseModel {
	private int id;
	private String name;//名称
	private String desp;//描述
	private int sequence;//序号
	private String mediaMp4Path;//mp4视频路径
	private String mediaOgvPath;//ogv视频路径
	private String mediaWebmPath;//webm视频路径
	private String imgPath;//图片路径
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getMediaMp4Path() {
		return mediaMp4Path;
	}
	public void setMediaMp4Path(String mediaMp4Path) {
		this.mediaMp4Path = mediaMp4Path;
	}
	public String getMediaOgvPath() {
		return mediaOgvPath;
	}
	public void setMediaOgvPath(String mediaOgvPath) {
		this.mediaOgvPath = mediaOgvPath;
	}
	public String getMediaWebmPath() {
		return mediaWebmPath;
	}
	public void setMediaWebmPath(String mediaWebmPath) {
		this.mediaWebmPath = mediaWebmPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
