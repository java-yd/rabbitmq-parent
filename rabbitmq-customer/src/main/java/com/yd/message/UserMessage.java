package com.yd.message;

import java.io.Serializable;

/**
 * @author yd
 *
 */
public class UserMessage implements Serializable{

	private  Integer  id;
	private  String   username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMessage(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserMessage [id=" + id + ", username=" + username + "]";
	}
	
}
