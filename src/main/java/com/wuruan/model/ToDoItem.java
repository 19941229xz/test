package com.wuruan.model;

import java.util.Date;

/**
 * 这是一个 表示  要做的事情的 类
 * @author xz
 *
 */
public class ToDoItem {
	
	private String id;

	public ToDoItem(String id, String title, String content, String date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 代办事项  标题
	 */
	private String title;
	
	/**
	 * 代办事项  内容
	 */
	private String content;
	
	/**
	 * 代办事项  截至时间
	 */
	private String date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ToDoItem(String title, String content, String date) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public ToDoItem() {
		super();
	}
	
	
	
	
	
}
