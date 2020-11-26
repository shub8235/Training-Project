package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.exception.AuthServiceException;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	// @Autowired
	// private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;

	@Override
	public void register(User user) {
		if (!userRepository.isUserPresent(user.getEmail())) {
			// String encryptPassword = passwordEncoder.encode(user.getPassword());
			// user.setPassword(encryptPassword);
			userRepository.save(user);
			// code to send email to recently registered customer
			try {
				emailService.sendSuccessfullRegistrationMail(user);
			} catch (AuthServiceException e) {
				throw new AuthServiceException("Email not Valid");
			}

		} else
			throw new AuthServiceException("User Already Registered");
	}

	@Override
	public User login(String email, String password) {
		try {
			if (!userRepository.isUserPresent(email)) {
				throw new AuthServiceException("User is not registered!");
			}
			// String endodedPassword = passwordEncoder.encode(password);
			User user = userRepository.findUserByEmailPassword(email, password);
			return user;
		} catch (EmptyResultDataAccessException e) {
			throw new AuthServiceException("Invalid Email/Password");
		}

	}

	@Override
	public User getUserById(int id) {
		return userRepository.fetchById(User.class, id);
	}

}
