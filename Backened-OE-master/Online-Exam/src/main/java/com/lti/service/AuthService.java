package com.lti.service;

import com.lti.entity.User;

public interface AuthService {

	void register(User user);

	User login(String email, String password);

	User getUserById(int id);

}