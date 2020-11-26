package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.Result;
import com.lti.entity.Subject;
import com.lti.entity.User;
import com.lti.entity.UserLevel;

@Repository
public class UserLevelRepoImpl extends GenericRepoImpl implements UserLevelRepo {

	@Override
	public void saveUserLevel(Result result) {
		UserLevel userLevel = null;
		int level = result.getLevel();

		Subject subject = result.getSubject();
		User user = result.getUser();
		if (isUserLevelPresent(user, subject)) {
			userLevel = getUserLevel(user, subject);
		} else {
			userLevel = new UserLevel();
		}
		userLevel.setSubject(subject);
		userLevel.setUser(user);
		if (level == 1) {
			if (result.getMarks() >= 8) {
				userLevel.setLevel1(true);
			}
		}
		if (level == 2) {
			if (result.getMarks() >= 7) {
				userLevel.setLevel2(true);
			}
		}
		if (level == 3) {
			if (result.getMarks() >= 6) {
				userLevel.setLevel3(true);
			}
		}
		entityManager.merge(userLevel);
	}

	@Override
	public boolean isUserLevelPresent(User user, Subject subject) {
		return (Long) entityManager
				.createQuery("select count(ul.id) from UserLevel ul where ul.user.id = :uId and ul.subject.id = :sId")
				.setParameter("uId", user.getId()).setParameter("sId", subject.getId()).getSingleResult() == 1 ? true
						: false;
	}

	@Override
	public UserLevel getUserLevel(User user, Subject subject) {
		if (isUserLevelPresent(user, subject)) {
			return (UserLevel) entityManager
					.createQuery("select ul from UserLevel ul where ul.user.id = :uId and ul.subject.id = :sId")
					.setParameter("uId", user.getId()).setParameter("sId", subject.getId()).getSingleResult();
		} else {
			return new UserLevel();
		}
	}
}
