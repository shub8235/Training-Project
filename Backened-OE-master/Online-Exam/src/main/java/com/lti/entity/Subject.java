package com.lti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oe_subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjectSeq")
	@SequenceGenerator(name = "subjectSeq", sequenceName = "SUBJECT_ID_SEQ", allocationSize = 1)
	@Column(name = "subject_id")
	private int id;

	@Column(name = "subject_name", unique = true)
	private String name;

	@Column(name = "subject_desc")
	private String description;

	@OneToMany(mappedBy = "subject")
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<UserLevel> userLevels;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy = "subject")
	@Cascade(CascadeType.DELETE)
	@JsonIgnore
	private List<Question> questions;

	@OneToMany(mappedBy = "subject")
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<Result> results;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserLevel> getUserLevels() {
		return userLevels;
	}

	public void setUserLevels(List<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}
