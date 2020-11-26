package com.lti.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.User;

@Repository
@Transactional
public class UserRepositoryImpl extends GenericRepoImpl implements UserRepository {

	@Override
	public User save(User user) {
		User modifiedUser = entityManager.merge(user);
		return modifiedUser;
	}

	@Override
	public User findUserByEmailPassword(String email, String password) {

		return (User) entityManager.createQuery("select u from User u where u.email = :email and u.password = :pwd")
				.setParameter("email", email).setParameter("pwd", password).getSingleResult();
	}

	@Override
	public boolean isUserPresent(String email) {
		return (Long) entityManager.createQuery("select count(u.id) from User u where u.email = :email")
				.setParameter("email", email).getSingleResult() == 1 ? true : false;
	}

	@Override
	public List<User> fetchAllUsers() {
		return (List<User>) entityManager.createQuery("select u from User u where u.role =:role")
				.setParameter("role", "student").getResultList();
	}

}
