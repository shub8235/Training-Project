package com.lti.service;

import java.util.List;

import com.lti.entity.Result;
import com.lti.entity.Subject;
import com.lti.entity.UserLevel;

public interface StudentService {

	List fetchQuestions(int subjectId, int level);

	Result saveResult(Result result);

	Result fetchResultByUserLevelAttepmts(int uId, int sId, int lvl, int attepmts);

	List<Result> fetchResultByUserAndLevel(int uId, int sId, int lvl);

	Long totalExamCount(int uId);

	Long examCountBySubject(int uId, int sId);

	Long examCountBySubjectLevel(int uId, int sId, int lvl);

	List<Result> fetchResultByUserAndSubject(int uId, int sId);

	List<Result> fetchResultByUser(int uId);

	boolean checkMaxAttempts(int uId, int sId, int lvl);

	List getDistincSubjectsByUser(int uId);

	Integer getMaxMarksForUserSubjectByLevel(int uId, String sName, int lvl);

	UserLevel getUserLevel(int uId, int sId);

	List getAttemptsForSubjectsByUser(int uId, int sId);

	Integer getMarksPerAttemptForUserSubjectByLevel(int uId, int sId, int attempt, int lvl);

	Result getLatestExam(int uId);

	List<Subject> getSubjectList();

}