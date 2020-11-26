package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ResultDto;
import com.lti.dto.Status;
import com.lti.entity.Result;
import com.lti.entity.Subject;
import com.lti.entity.User;
import com.lti.entity.UserLevel;
import com.lti.service.AdminService;
import com.lti.service.AuthService;
import com.lti.service.StudentService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private AuthService authService;

	@GetMapping("/fetch-subject")
	public List fetchAllSubjects() {
		return adminService.fetchAllSubjects();
	}

	@GetMapping("/fetch-question")
	public List fetchSubjects(@RequestParam("id") int id) {
		return adminService.fetchQuestionBySubject(id);
	}

	@GetMapping("/fetch-questionByLevel")
	public List fetchQuestionByLevel(@RequestParam("sId") int subjectId, @RequestParam("lvl") int level) {
		return studentService.fetchQuestions(subjectId, level);
	}

	@PostMapping("set-result")
	public Status setResult(@RequestBody ResultDto resultDto) {
		Status status = new Status();
		try {
			Result result = new Result();
			User user = authService.getUserById(resultDto.getUserId());
			Subject subject = adminService.getSubject(resultDto.getSubjectId());
			result.setLevel(resultDto.getLevel());
			result.setUser(user);
			result.setSubject(subject);
			result.setMarks(resultDto.getMarks());
			studentService.saveResult(result);
			status.setStatus(true);
			status.setMsg("Result Saved...");

		} catch (Exception e) {
			status.setStatus(false);
			status.setMsg("Result saved Failed.");
		}

		return status;

	}

	@PostMapping("get-result")
	public List<Result> getResult(@RequestBody ResultDto resultDto) {
		List list = studentService.fetchResultByUserAndLevel(resultDto.getUserId(), resultDto.getSubjectId(),
				resultDto.getLevel());
		return list;
	}

	@PostMapping("get-single-result")
	public Result getSingleResult(@RequestBody ResultDto resultDto) {
		Result result = studentService.fetchResultByUserLevelAttepmts(resultDto.getUserId(), resultDto.getSubjectId(),
				resultDto.getLevel(), resultDto.getAttepmts());
		return result;
	}

	@PostMapping("check-allowed-attempt")
	public Status checkAllowedAttempts(@RequestBody ResultDto resultDto) {
		Status status = new Status();
		if (studentService.checkMaxAttempts(resultDto.getUserId(), resultDto.getSubjectId(), resultDto.getLevel())) {
			status.setStatus(false);
			status.setMsg(
					"You reached max allowed attempts for this level. Please contact admin for further assistance.");
		} else {
			status.setStatus(true);
			status.setMsg("Attempt Allowed.");
		}
		return status;
	}

	@GetMapping("/get-resultByUser")
	public List<Result> getResultByUser(@RequestParam("id") int uId) {
		return studentService.fetchResultByUser(uId);
	}

	@PostMapping("/get-resultByUserSubject")
	public List<Result> getResultByUserSubject(@RequestBody ResultDto resultDto) {
		return studentService.fetchResultByUserAndSubject(resultDto.getUserId(), resultDto.getSubjectId());
	}

	@PostMapping("/exam-totalCount")
	public Long totalExamCountForAStudent(@RequestBody ResultDto resultDto) {
		return studentService.totalExamCount(resultDto.getUserId());
	}

	@PostMapping("/exam-totalCountForASubject")
	public Long totalExamCountForASubject(@RequestBody ResultDto resultDto) {
		return studentService.examCountBySubject(resultDto.getUserId(), resultDto.getSubjectId());
	}

	@PostMapping("/exam-totalCountForASubjectLevel")
	public Long totalExamCountForASubjectLevel(@RequestBody ResultDto resultDto) {
		return studentService.examCountBySubjectLevel(resultDto.getUserId(), resultDto.getSubjectId(),
				resultDto.getLevel());
	}

	@GetMapping("/get-distinct-subjectsByUser")
	public List getDistincSubjectByUSer(@RequestParam("id") int uId) {
		return studentService.getDistincSubjectsByUser(uId);
	}

	@GetMapping("/get-resultForUserBySubject")
	public List getResultListforUser(@RequestParam("uId") int uId, @RequestParam("sId") int sId) {
		return studentService.fetchResultByUserAndSubject(uId, sId);
	}

	@GetMapping("/get-maxMarksByUserSubject")
	public int getMaxMarksForUserSubjectByLevel(@RequestParam("id") int uId, @RequestParam("sName") String sName,
			@RequestParam("lvl") int lvl) {
		if (studentService.getMaxMarksForUserSubjectByLevel(uId, sName, lvl) != null) {
			return studentService.getMaxMarksForUserSubjectByLevel(uId, sName, lvl);
		} else {
			return 0;
		}

	}

	@GetMapping("get-userLevel")
	public UserLevel getUserLevel(@RequestParam("uId") int uId, @RequestParam("sId") int sId) {
		return studentService.getUserLevel(uId, sId);
	}

	@GetMapping("get-attemptsForSubjectByUser")
	public List getAttemptForSubjefctByUser(@RequestParam("uId") int uId, @RequestParam("sId") int sId) {
		return studentService.getAttemptsForSubjectsByUser(uId, sId);
	}

	@GetMapping("/get-MarksPerAttemptByUserSubject")
	public int getMarksPerAttemptForUserSubjectByLevel(@RequestParam("uId") int uId, @RequestParam("sId") int sId,
			@RequestParam("atmpt") int attempt, @RequestParam("lvl") int lvl) {
		if (studentService.getMarksPerAttemptForUserSubjectByLevel(uId, sId, attempt, lvl) != null) {
			return studentService.getMarksPerAttemptForUserSubjectByLevel(uId, sId, attempt, lvl);
		} else {
			return 0;
		}

	}

	@GetMapping("get-latestExam")
	public Result getLatestExam(@RequestParam("id") int uId) {
		return studentService.getLatestExam(uId);
	}
	
	@GetMapping("get-subjectsForUser")
	public List<Subject> getSubjectsForUser(){
		return studentService.getSubjectList();
	}

}
