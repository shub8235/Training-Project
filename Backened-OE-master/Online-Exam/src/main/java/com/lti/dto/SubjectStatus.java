package com.lti.dto;

import com.lti.entity.Subject;

public class SubjectStatus extends Status {

	private Subject subject;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
