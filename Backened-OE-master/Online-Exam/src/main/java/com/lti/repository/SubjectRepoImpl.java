package com.lti.repository;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Subject;

@Repository
@Transactional
public class SubjectRepoImpl extends GenericRepoImpl implements SubjectRepo {

	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public Subject save(Subject subject) {
		Subject modifiedSubject = entityManager.merge(subject);
		return modifiedSubject;
	}

	@Override
	public boolean isSubjectPresent(String name) {
		return (Long) entityManager.createQuery("select count(s.id) from Subject s where s.name = :name")
				.setParameter("name", name).getSingleResult() == 1 ? true : false;
	}

	@Override
	public List<Subject> fetchAll() {
		String jpql = "select s from Subject s order by s.id";
		List list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Subject> fetchSubjectsHavingQuestions() {
		List<Subject> list = fetchAll();
		List<Subject> newList = new ArrayList<Subject>();
		for (Subject subject : list) {
			System.out.println(questionRepo.noOfQuestion(subject.getId()));
			if (questionRepo.noOfQuestion(subject.getId()) >= 10) {
				newList.add(subject);
			}
		}
		return newList;
	}

}
