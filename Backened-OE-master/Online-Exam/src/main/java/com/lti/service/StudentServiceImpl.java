package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Result;

import com.lti.entity.Subject;
import com.lti.entity.User;

import com.lti.entity.UserLevel;
import com.lti.repository.QuestionRepo;
import com.lti.repository.ResultRepo;
import com.lti.repository.SubjectRepo;
import com.lti.repository.UserLevelRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private ResultRepo resultRepo;

	@Autowired
	private UserLevelRepo userLevelRepo;

	@Autowired
	private SubjectRepo subjectRepo;

	@Override
	public List fetchQuestions(int subjectId, int level) {
		return questionRepo.fetchQuestionBySubjectAndLevel(subjectId, level);
	}

	@Override
	@Transactional
	public Result saveResult(Result result) {
		return resultRepo.save(result);

	}

	@Override
	public List<Result> fetchResultByUserAndLevel(int uId, int sId, int lvl) {
		return resultRepo.fetchByUserSubjectAndLevel(uId, sId, lvl);
	}

	@Override

	public Result fetchResultByUserLevelAttepmts(int uId, int sId, int lvl, int attepmts) {
		return resultRepo.fetchByUserSubjectLevelAttempts(uId, sId, lvl, attepmts);
	}

	@Override
	public boolean checkMaxAttempts(int uId, int sId, int lvl) {
		try {
			if (resultRepo.getMaxAttempts(uId, sId, lvl) == 4) {
				return true;
			} else
				return false;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public Long totalExamCount(int uId) {
		return (Long) resultRepo.noOfExamsTaken(uId);
	}

	@Override
	public Long examCountBySubject(int uId, int sId) {
		return (Long) resultRepo.noOfExamsTakenForASubject(uId, sId);
	}

	@Override
	public Long examCountBySubjectLevel(int uId, int sId, int lvl) {
		return (Long) resultRepo.noOfExamsTakenForASubjectLevel(uId, sId, lvl);
	}

	@Override
	public List<Result> fetchResultByUserAndSubject(int uId, int sId) {
		return resultRepo.fetchByUserSubject(uId, sId);
	}

	@Override
	public List<Result> fetchResultByUser(int uId) {
		return resultRepo.fetchResultsByUser(uId);

	}

	@Override
	public List getDistincSubjectsByUser(int uId) {
		return resultRepo.fetchDistinctSubjectByUser(uId);
	}

	@Override
	public Integer getMaxMarksForUserSubjectByLevel(int uId, String sName, int lvl) {
		try {
			return resultRepo.fetchMaxMarksByLevelSubjectAndUser(uId, sName, lvl);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public UserLevel getUserLevel(int uId, int sId) {
		User user = userLevelRepo.fetchById(User.class, uId);
		Subject subject = userLevelRepo.fetchById(Subject.class, sId);

		return userLevelRepo.getUserLevel(user, subject);
	}

	@Override
	public List getAttemptsForSubjectsByUser(int uId, int sId) {
		return resultRepo.fetchAttemptsForSubjectByUser(uId, sId);
	}

	@Override
	public Integer getMarksPerAttemptForUserSubjectByLevel(int uId, int sId, int attempt, int lvl) {
		try {
			return resultRepo.fetchMarksPerAttemptByLevelSubjectAndUser(uId, sId, attempt, lvl);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Result getLatestExam(int uId) {
		try {
			return resultRepo.fetchLatestExam(uId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Subject> getSubjectList() {
		return subjectRepo.fetchSubjectsHavingQuestions();
	}
}
