package com.dantri.items;

public class Item {

	private String title;
	private String link;
	private String desc;
	private String pubDate;
	private String position;
	
	public Item(String position) {
		this.position = position;
		title = "";
		link = "";
		desc = "";
		pubDate = "";
	}

	public Item() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getPosition() {
		return position;
	}
	
	public Item setTitle1(String title){
		this.title = title;
		return this;
	}
	
	public Item setDesc1(String desc){
		this.desc = desc;
		return this;
	}
	
	public Item setLink1(String link){
		this.link = link;
		return this;
	}

}
