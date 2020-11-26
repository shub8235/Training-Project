package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ReadFileDto;
import com.lti.dto.SearchDto;
import com.lti.dto.Status;
import com.lti.dto.SubjectStatus;
import com.lti.entity.Question;
import com.lti.entity.Subject;
import com.lti.entity.User;
import com.lti.exception.AdminServiceException;

import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add-subject")
	public SubjectStatus addSubject(@RequestBody Subject subject) {
		SubjectStatus status = new SubjectStatus();
		try {
			Subject newSubject = adminService.addSubject(subject);

			status.setMsg("Subject Added Successful..");
			status.setStatus(true);
			status.setSubject(newSubject);
			return status;
		} catch (AdminServiceException e) {
			e.printStackTrace();
			status.setMsg("Failed. " + e.getMessage());
			status.setStatus(false);
			return status;

		}
	}

	@DeleteMapping("/delete-subject")
	public Status deleteSubject(@RequestParam("id") int id) {
		Status status = new Status();
		try {
			adminService.deleteSubject(id);
			status.setMsg("Subject Deleted Successfully..");
			status.setStatus(true);
			return status;
		} catch (AdminServiceException e) {
			e.printStackTrace();
			status.setMsg("Failed. " + e.getMessage());
			status.setStatus(false);
			return status;
		}
	}

	@PostMapping("/add-question")
	public Status addQuestion(@RequestBody Question question, @RequestParam("id") int id) {
		Status status = new Status();
		try {
			// Question question = questionDto.getQuestion();
			question.setSubject(adminService.getSubject(id));
			adminService.addQuestion(question);
			status.setMsg("Question Added Successful..");
			status.setStatus(true);
			return status;
		} catch (AdminServiceException e) {
			e.printStackTrace();
			status.setMsg("Failed. " + e.getMessage());
			status.setStatus(false);
			return status;
		}

	}

	@DeleteMapping("/delete-question")
	public Status deleteQuestion(@RequestParam("id") int id) {
		Status status = new Status();
		try {
			adminService.deleteQuestion(id);
			status.setMsg("Question Deleted Successfully..");
			status.setStatus(true);
			return status;
		} catch (AdminServiceException e) {
			e.printStackTrace();
			status.setMsg("Failed. " + e.getMessage());
			status.setStatus(false);
			return status;
		}
	}

	@PostMapping("/question-file")
	public Status readFile(ReadFileDto readFileDto) {
		Status status = new Status();

		boolean isUploaded = adminService.readFile(readFileDto.getSubjectId(), readFileDto.getQuestionFile());
		if (isUploaded) {
			status.setStatus(true);
			status.setMsg("Questions Uploaded Successfully");

		} else {
			status.setStatus(false);
			status.setMsg("Questions Upload Failed...");

		}

		return status;
	}

	@PostMapping("/search-students")
	public List<String> searchStudentsAllCriteria(@RequestBody SearchDto searchDto) {
		return adminService.fetchStudentsAllCriteria(searchDto.getSubjectName(), searchDto.getState(),
				searchDto.getCity(), searchDto.getLevel(), searchDto.getMarks());
	}

	@GetMapping("/get-allSubjectName")
	public List<String> getAllSubjectName() {
		return adminService.getAllSubjectsName();
	}

	@GetMapping("/get-avgMarksBySubject")
	public double getAvgMarksBySubject(@RequestParam("sName") String sName, @RequestParam("lvl") int level) {
		if (adminService.getAvgMarksBySubjectAndLevel(sName, level) != null) {
			return adminService.getAvgMarksBySubjectAndLevel(sName, level);
		} else {
			return 0;
		}
	}

	@GetMapping("/get-allUsers")
	public List<User> getUsers() {
		return adminService.getAllUsers();
	}

}
