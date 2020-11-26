package com.lti.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.lti.entity.Result;

public interface ResultRepo extends GenericRepo {

	Result save(Result result);

	boolean isAttempted(int uId, int sId, int lvl);

	List<Result> fetchAll();

	List<Result> fetchResultsByUser(int uId);

	List<Result> fetchResultsByLevel(int lvl);

	List<Result> fetchByUserSubjectAndLevel(int uId, int sId, int lvl);

	Long noOfExamsTakenForASubject(int uId, int sId);

	Long noOfExamsTakenForASubjectLevel(int uId, int sId, int lvl);

	Long noOfExamsTaken(int uId);

	List<Result> fetchByUserSubject(int uId, int sId);

	Result fetchByUserSubjectLevelAttempts(int uId, int sId, int lvl, int attempts);

	int getMaxAttempts(int uId, int sId, int lvl) throws EmptyResultDataAccessException;

	List<String> fetchByCriteria(String subName, String state, String city, int level, int marks);

	List fetchDistinctSubjectByUser(int uId);

	Integer fetchMaxMarksByLevelSubjectAndUser(int uId, String sName, int level);

	List fetchAttemptsForSubjectByUser(int uId, int sId);

	Integer fetchMarksPerAttemptByLevelSubjectAndUser(int uId, int sId, int attempt, int level)
			throws EmptyResultDataAccessException;

	Double fetchAvgMarksByLevelSubject(String sName, int level) throws EmptyResultDataAccessException;

	Result fetchLatestExam(int uId) throws EmptyResultDataAccessException;

}