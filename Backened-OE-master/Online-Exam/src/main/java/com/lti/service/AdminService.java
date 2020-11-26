package com.lti.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lti.entity.Question;
import com.lti.entity.Subject;
import com.lti.entity.User;

public interface AdminService {

	Subject addSubject(Subject subject);

	void deleteSubject(int id);

	public void addQuestion(Question question);

	void deleteQuestion(int id);

	Subject getSubject(int id);

	List<Subject> fetchAllSubjects();

	List fetchQuestionBySubject(int subjectId);

	boolean readFile(int id, MultipartFile file);

	List<String> fetchStudentsAllCriteria(String subName, String state, String city, int level, int marks);

	List<String> getAllSubjectsName();

	Double getAvgMarksBySubjectAndLevel(String sName, int level);

	List<User> getAllUsers();

}