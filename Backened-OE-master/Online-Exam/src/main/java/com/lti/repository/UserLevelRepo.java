package com.lti.repository;

import com.lti.entity.Result;
import com.lti.entity.Subject;
import com.lti.entity.User;
import com.lti.entity.UserLevel;

public interface UserLevelRepo extends GenericRepo{

	void saveUserLevel(Result result);

	//UserLevel fetchUserLevelByUserAndSubject(int uId, int sId);

	UserLevel getUserLevel(User user, Subject subject);

	boolean isUserLevelPresent(User user, Subject subject);

}