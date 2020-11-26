package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status;

import com.lti.entity.User;
import com.lti.exception.AuthServiceException;

import com.lti.service.AuthService;
import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthService authService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/register")
	public @ResponseBody Status register(@RequestBody User user) {
		Status status = new Status();
		try {
			authService.register(user);
			status.setMsg("Registration Successful..");
			status.setStatus(true);
			return status;
		} catch (AuthServiceException e) {
			e.printStackTrace();
			status.setMsg("Registration Failed. " + e.getMessage());
			status.setStatus(false);
			return status;
		}
	}

	@PostMapping("/login")
	public @ResponseBody LoginStatus login(@RequestBody Login login) {
		try {
			User user = authService.login(login.getEmail(), login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMsg("Login Successfull");
			loginStatus.setUser(user);
			return loginStatus;
		} catch (AuthServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMsg(e.getMessage());
			return loginStatus;
		}
	}

	@GetMapping("/reset-password")
	public @ResponseBody Status resetPassword(@RequestParam("email") String email) {
		Status status = new Status();
		try {
			emailService.sendMailPasswordReset(email);
			status.setStatus(true);
			status.setMsg("Password reset Successfull");
			return status;
		} catch (AuthServiceException e) {
			status.setStatus(false);
			status.setMsg("Password Reset Failed..");
			return status;
		}
	}

}
