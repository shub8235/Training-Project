package com.lti.dto;

import com.lti.entity.Question;

public class QuestionDto {

	private Question question;
	private int subjectId;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

}
