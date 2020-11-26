package com.lti.dto;

import com.lti.entity.User;

public class LoginStatus extends Status {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}