package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oe_questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionSeq")
	@SequenceGenerator(name = "questionSeq", sequenceName = "QUESTION_ID_SEQ", allocationSize = 1)
	@Column(name = "question_id")
	private int id;
	@Column(name = "lvl")
	private int level;
	@Column(name = "question")
	private String ques;
	@Column(name = "option_A")
	private String optionA;
	@Column(name = "option_B")
	private String optionB;
	@Column(name = "option_C")
	private String optionC;
	@Column(name = "option_D")
	private String optionD;
	@Column(name = "correct_ans")
	private String correctAns;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	@JsonIgnore
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

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
