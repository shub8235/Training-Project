package com.lti.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "oe_results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultSeq")
	@SequenceGenerator(name = "resultSeq", sequenceName = "RESULT_ID_SEQ", allocationSize = 1)
	@Column(name = "result_id")
	private int id;
	@Column(name = "lvl")
	private int level;
	private int marks;

	@Column(name = "no_of_attempts")
	private int attempts = 1;
	@Column(name = "date_of_attempt")
	private LocalDateTime dateOfAttempt = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	// @JsonIgnore
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	// @JsonIgnore

	private Subject subject;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public LocalDateTime getDateOfAttempt() {
		return dateOfAttempt;
	}

	public void setDateOfAttempt(LocalDateTime dateOfAttempt) {
		this.dateOfAttempt = dateOfAttempt;
	}

}
