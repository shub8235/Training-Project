package com.lti.dto;

import java.time.LocalDate;

public class ResultDto {

	private int userId;
	private int subjectId;
	private int level;
	private int marks;
	private int attepmts;
	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getAttepmts() {
		return attepmts;
	}

	public void setAttepmts(int attepmts) {
		this.attepmts = attepmts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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

}
