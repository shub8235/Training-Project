package com.lti.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lti.dto.Status;
import com.lti.entity.Question;
import com.lti.entity.Result;
import com.lti.entity.Subject;
import com.lti.entity.User;
import com.lti.exception.AdminServiceException;
import com.lti.repository.QuestionRepo;
import com.lti.repository.ResultRepo;
import com.lti.repository.SubjectRepo;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SubjectRepo subjectRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private ResultRepo resultRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Subject addSubject(Subject subject) {

		if (!subjectRepo.isSubjectPresent(subject.getName())) {
			return subjectRepo.save(subject);
		} else {
			throw new AdminServiceException("Subject already present");
		}

	}

	@Override
	public Subject getSubject(int id) {
		Subject subject = subjectRepo.fetchById(Subject.class, id);
		if (subject == null) {
			throw new AdminServiceException("Subject not found");
		} else {
			return subject;
		}
	}

	@Override
	public void deleteSubject(int id) {
		subjectRepo.deleteById(Subject.class, id);
	}

	@Override
	public List fetchAllSubjects() {
		return subjectRepo.fetchAll();
	}

	@Override
	public List fetchQuestionBySubject(int subjectId) {
		return questionRepo.fetchQuestionsBySubject(subjectId);
	}

	@Override
	public void addQuestion(Question question) {

		questionRepo.save(question);

	}

	@Override
	public void deleteQuestion(int id) {
		questionRepo.deleteById(Question.class, id);
	}

	@Override
	public boolean readFile(int id, MultipartFile file) {
		Subject subject = subjectRepo.fetchById(Subject.class, id);

		String fileUploadLocation = "C:/Gladiator Files/";
		String fileName = file.getOriginalFilename();

		String targetFile = fileUploadLocation + fileName;

		try {
			file.transferTo(new File(targetFile));

			if (questionRepo.addQuestionFromCsv(targetFile, subject)) {
				return true;
			}

			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

//	Added
	@Override
	public List<String> fetchStudentsAllCriteria(String subName, String state, String city, int level, int marks) {
		return resultRepo.fetchByCriteria(subName, state, city, level, marks);
	}

	@Override
	public List<String> getAllSubjectsName() {
		List<Subject> list = subjectRepo.fetchAll();
		List<String> nameList = new ArrayList<String>();
		for (Subject subject : list) {
			nameList.add(subject.getName());
		}
		return nameList;
	}

	@Override
	public Double getAvgMarksBySubjectAndLevel(String sName, int level) {
		try {
			Double marks = (Double) resultRepo.fetchAvgMarksByLevelSubject(sName, level);
			return marks;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.fetchAllUsers();
	}

}
