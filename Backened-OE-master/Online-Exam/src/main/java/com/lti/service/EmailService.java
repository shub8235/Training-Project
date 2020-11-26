package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.exception.AuthServiceException;
import com.lti.repository.UserRepository;

@Service
public class EmailService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailSender mailSender;

	public void sendMailPasswordReset(String email) {

		if (userRepository.isUserPresent(email)) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ascw.upes@gmail.com");
			message.setTo(email);
			message.setSubject("Reset Password Link");
			message.setText("Follow the link to reset your password");
			mailSender.send(message);

		} else {
			throw new AuthServiceException("Email not Registered");
		}

	}

	public void sendSuccessfullRegistrationMail(User user) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ascw.upes@gmail.com");
			message.setTo(user.getEmail());
			message.setSubject("Welcome to Geeky Ranks!!!");
			message.setText("Matching developers with great companies..");
			mailSender.send(message);
		} catch (AuthServiceException e) {
			throw new AuthServiceException("Email not valid");
		}

	}

}
