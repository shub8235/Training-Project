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
@Table(name = "oe_user_level")
public class UserLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userLevelSeq")
	@SequenceGenerator(name = "userLevelSeq", sequenceName = "USER_LEVEL_ID_SEQ", allocationSize = 1)
	@Column(name = "user_level_id")
	private int id;
	private boolean level1 = false;
	private boolean level2 = false;
	private boolean level3 = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

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

	public boolean isLevel1() {
		return level1;
	}

	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}

	public boolean isLevel2() {
		return level2;
	}

	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}

	public boolean isLevel3() {
		return level3;
	}

	public void setLevel3(boolean level3) {
		this.level3 = level3;
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

}
