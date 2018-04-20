package org.ram.testwebservice.messengers.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Comment {
	
	private long id;
	private String messages;
	private Date created;
	private String author;

	
	public Comment(){
		
	}
	
	public Comment(long id,String messages,String author){
		
		this.id = id;
		this.messages = messages;
		this.author = author;
		this.created = new Date();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
