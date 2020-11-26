package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class ReadFileDto {

	private int subjectId;
	private MultipartFile questionFile;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public MultipartFile getQuestionFile() {
		return questionFile;
	}

	public void setQuestionFile(MultipartFile questionFile) {
		this.questionFile = questionFile;
	}

}
