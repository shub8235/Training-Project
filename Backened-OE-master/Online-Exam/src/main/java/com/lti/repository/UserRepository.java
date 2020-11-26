package com.lti.repository;

import java.util.List;

import com.lti.entity.User;

public interface UserRepository extends GenericRepo {

	User save(User user);

	User findUserByEmailPassword(String email, String password);

	boolean isUserPresent(String email);

	List<User> fetchAllUsers();

}