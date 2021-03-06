package com.dag.robot.entities;

// Generated 2015-5-21 18:43:44 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Field generated by hbm2java
 */
@Entity
@Table(name = "field", catalog = "db_expert_robot")
public class Field implements java.io.Serializable {

	private Integer fieldId;
	private String name;
	private String description;
	private Set<RelFieldTopic> relFieldTopics = new HashSet<RelFieldTopic>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<Expert> experts = new HashSet<Expert>(0);

	public Field() {
	}

	public Field(String name) {
		this.name = name;
	}

	public Field(String name, String description,
			Set<RelFieldTopic> relFieldTopics, Set<User> users,
			Set<Expert> experts) {
		this.name = name;
		this.description = description;
		this.relFieldTopics = relFieldTopics;
		this.users = users;
		this.experts = experts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fieldId", unique = true, nullable = false)
	public Integer getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
	public Set<RelFieldTopic> getRelFieldTopics() {
		return this.relFieldTopics;
	}

	public void setRelFieldTopics(Set<RelFieldTopic> relFieldTopics) {
		this.relFieldTopics = relFieldTopics;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "fields")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
	public Set<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(Set<Expert> experts) {
		this.experts = experts;
	}

}
