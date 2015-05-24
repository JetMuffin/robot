package com.dag.robot.entities;

// Generated 2015-5-22 21:30:54 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Conference generated by hbm2java
 */
@Entity
@Table(name = "conference", catalog = "db_expert_robot")
public class Conference implements java.io.Serializable {

	private Integer conferenceId;
	private String name;
	private int rate;
	private Set<Paper> papers = new HashSet<Paper>(0);

	public Conference() {
	}

	public Conference(String name, int rate) {
		this.name = name;
		this.rate = rate;
	}

	public Conference(String name, int rate, Set<Paper> papers) {
		this.name = name;
		this.rate = rate;
		this.papers = papers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "conferenceId", unique = true, nullable = false)
	public Integer getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "rate", nullable = false)
	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conference")
	public Set<Paper> getPapers() {
		return this.papers;
	}

	public void setPapers(Set<Paper> papers) {
		this.papers = papers;
	}

}
