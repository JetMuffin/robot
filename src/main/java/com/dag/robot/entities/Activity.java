package com.dag.robot.entities;

// Generated 2015-5-18 21:02:56 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Activity generated by hbm2java
 */
@Entity
@Table(name = "activity", catalog = "db_expert_robot")
public class Activity implements java.io.Serializable {

	private Integer activityId;
	private Date time;
	private String content;
	private Set<Expert> experts = new HashSet<Expert>(0);

	public Activity() {
	}

	public Activity(String content) {
		this.content = content;
	}

	public Activity(Date time, String content, Set<Expert> experts) {
		this.time = time;
		this.content = content;
		this.experts = experts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "activityId", unique = true, nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "activities")
	public Set<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(Set<Expert> experts) {
		this.experts = experts;
	}

}
