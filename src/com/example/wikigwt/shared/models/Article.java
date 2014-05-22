package com.example.wikigwt.shared.models;

import java.io.Serializable;
import java.util.Date;




public class Article implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String desc;
	
	
	public Article() {
	}
	
	public Article(Long id, String title, String desc) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
	}
	public Article(String title, String desc) {
		super();
		this.id = new Date().getTime();
		this.title = title;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}
