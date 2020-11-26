package com.lti.entity;

import java.time.LocalDate;
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
@Table(name = "oe_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "USER_ID_SEQ", allocationSize = 1)
	@Column(name = "user_id")
	private int id;
	private String role = "student";
	private String name;
	@Column(unique = true)
	private String email;
	@Column(name = "mobile_no")
	private long mobNo;
	@Column(name = "date_of_birth")
	private LocalDate dob;
	@Column(name = "year_of_completion")
	private int yoc;
	private String qualification;
	private String city;
	private String state;
	private String password;

	@OneToMany(mappedBy = "user")
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<UserLevel> userLevels;

	@OneToMany(mappedBy = "user")
	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private List<Result> results;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getYoc() {
		return yoc;
	}

	public void setYoc(int yoc) {
		this.yoc = yoc;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobNo() {
		return mobNo;
	}

	public void setMobNo(long mobNo) {
		this.mobNo = mobNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserLevel> getUserLevels() {
		return userLevels;
	}

	public void setUserLevels(List<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}
