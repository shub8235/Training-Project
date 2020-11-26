package com.lti.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.lti.entity.Result;

@Repository
public class ResultRepoImpl extends GenericRepoImpl implements ResultRepo {

	@Autowired
	private UserLevelRepo userLevelRepo;

	@Override
	public Result save(Result result) {
		userLevelRepo.saveUserLevel(result);
		System.out.println(isAttempted(result.getUser().getId(), result.getSubject().getId(), result.getLevel()));
		if (isAttempted(result.getUser().getId(), result.getSubject().getId(), result.getLevel())) {
			result.setAttempts(
					getMaxAttempts(result.getUser().getId(), result.getSubject().getId(), result.getLevel()) + 1);
		}
		return super.save(result);
	}

	@Override
	public boolean isAttempted(int uId, int sId, int lvl) {
		String jpql = "select count(r.id) from Result r where r.user.id = :uId and r.subject.id = :sId and r.level = :lvl";
		return (Long) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("lvl", lvl).getSingleResult() > 0 ? true : false;

	}

	@Override
	public int getMaxAttempts(int uId, int sId, int lvl) throws EmptyResultDataAccessException {
		String jpql = "select max(r.attempts) from Result r where r.user.id = :uId and r.subject.id = :sId and r.level = :lvl group by r.user.id";
		return (Integer) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("lvl", lvl).getSingleResult();
	}

	@Override
	public List<Result> fetchByUserSubjectAndLevel(int uId, int sId, int lvl) {
		String jpql = "select r from Result r where r.user.id = :uId and r.subject.id = :sId and r.level = :lvl";

		List list = entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("lvl", lvl).getResultList();
		return list;

	}

	@Override
	public Result fetchByUserSubjectLevelAttempts(int uId, int sId, int lvl, int attempts) {
		String jpql = "select r from Result r where r.user.id = :uId and r.subject.id = :sId and r.level = :lvl and r.attempts = :attempts";
		return (Result) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("lvl", lvl).setParameter("attempts", attempts).getSingleResult();
	}

	@Override
	public List<Result> fetchAll() {
		String jpql = "select r from Result r";
		List list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Result> fetchResultsByUser(int uId) {
		String jpql = "select r from Result r where r.user.id = :uId order by r.subject.name, r.level, r.attempts,r.marks, r.dateOfAttempt";
		List list = entityManager.createQuery(jpql).setParameter("uId", uId).getResultList();
		return list;
	}

	@Override
	public List<Result> fetchResultsByLevel(int lvl) {
		String jpql = "select r from Result r where r.level = :lvl";
		List list = entityManager.createQuery(jpql).setParameter("lvl", lvl).getResultList();
		return list;
	}

	@Override
	public Long noOfExamsTaken(int uId) {
		String jpql = "select count(user.id) from Result r where r.user.id =: uId";
		return (Long) entityManager.createQuery(jpql).setParameter("uId", uId).getSingleResult();
	}

	@Override
	public Long noOfExamsTakenForASubject(int uId, int sId) {
		String jpql = "select count(user.id) from Result r where r.user.id =: uId and r.subject.id = :sId";
		return (Long) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.getSingleResult();
	}

	@Override
	public Long noOfExamsTakenForASubjectLevel(int uId, int sId, int lvl) {
		String jpql = "select count(user.id) from Result r where r.user.id = :uId and r.subject.id = :sId and r.level = :lvl";
		return (Long) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("lvl", lvl).getSingleResult();
	}

	@Override
	public List<Result> fetchByUserSubject(int uId, int sId) {
		String jpql = "select r from Result r where r.user.id =: uId and r.subject.id = :sId order by r.level, r.attempts";
		List list = entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId).getResultList();
		return list;
	}

	// search result all criteria applied
	@Override
	public List<String> fetchByCriteria(String subName, String state, String city, int level, int marks) {

		String jpql = "select r.user.name from Result r where r.subject.name =: subname and r.user.state = :state and r.user.city = :city and r.level =: level and r.marks =: marks";
		List list = entityManager.createQuery(jpql).setParameter("subname", subName).setParameter("state", state)
				.setParameter("city", "city").setParameter("level", level).setParameter("marks", marks).getResultList();
		return list;

	}

	@Override
	public List fetchDistinctSubjectByUser(int uId) {
		String jpql = "select distinct r.subject from Result r where r.user.id = :uId";
		List list = entityManager.createQuery(jpql).setParameter("uId", uId).getResultList();
		return list;
	}

	@Override
	public Integer fetchMaxMarksByLevelSubjectAndUser(int uId, String sName, int level)
			throws EmptyResultDataAccessException {
		String jpql = "select max(r.marks) from Result r join Subject s on r.subject.id = s.id where r.user.id = :uId and s.name = :sName and r.level = :lvl group by s.name order by s.name";
		Integer marks = (Integer) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sName", sName)
				.setParameter("lvl", level).getSingleResult();
		return marks;
	}

	@Override
	public List fetchAttemptsForSubjectByUser(int uId, int sId) {
		String jpql = "select distinct r.attempts from Result r where r.user.id = :uId and r.subject.id = :sId";
		List list = entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId).getResultList();
		return list;
	}

	@Override
	public Integer fetchMarksPerAttemptByLevelSubjectAndUser(int uId, int sId, int attempt, int level)
			throws EmptyResultDataAccessException {
		String jpql = "select r.marks from Result r where r.user.id = :uId and r.subject.id = :sId and r.attempts = :attempt and r.level = :lvl order by r.attempts";
		Integer marks = (Integer) entityManager.createQuery(jpql).setParameter("uId", uId).setParameter("sId", sId)
				.setParameter("attempt", attempt).setParameter("lvl", level).getSingleResult();
		return marks;
	}

	@Override
	public Double fetchAvgMarksByLevelSubject(String sName, int level) throws EmptyResultDataAccessException {
		String jpql = "select avg(r.marks) from Result r join Subject s on r.subject.id = s.id where s.name = :sName and r.level = :lvl group by s.name order by s.name";
		Double marks = (Double) entityManager.createQuery(jpql).setParameter("sName", sName).setParameter("lvl", level)
				.getSingleResult();
		return marks;
	}

	@Override
	public Result fetchLatestExam(int uId) throws EmptyResultDataAccessException {
		String jpql = "select r from Result r where r.user.id = :uId order by r.dateOfAttempt desc";
		return (Result) entityManager.createQuery(jpql).setParameter("uId", uId).setMaxResults(1).getSingleResult();
	}

}
