package com.lti.repository;

import java.util.List;

import com.lti.entity.Subject;

public interface SubjectRepo extends GenericRepo {

	Subject save(Subject subject);

	boolean isSubjectPresent(String name);

	List<Subject> fetchAll();

	List<Subject> fetchSubjectsHavingQuestions();

}