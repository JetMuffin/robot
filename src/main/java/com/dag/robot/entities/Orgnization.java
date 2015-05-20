package com.dag.robot.entities;

// Generated 2015-5-20 18:46:20 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Orgnization generated by hbm2java
 */
@Entity
@Table(name = "orgnization", catalog = "db_expert_robot")
public class Orgnization implements java.io.Serializable {

	private Integer orgId;
	private Orgnization orgnization;
	private String name;
	private String address;
	private Set<Paper> papers = new HashSet<Paper>(0);
	private Set<Expert> experts = new HashSet<Expert>(0);
	private Set<Patent> patents = new HashSet<Patent>(0);
	private Set<Orgnization> orgnizations = new HashSet<Orgnization>(0);

	public Orgnization() {
	}

	public Orgnization(String name) {
		this.name = name;
	}

	public Orgnization(Orgnization orgnization, String name, String address,
			Set<Paper> papers, Set<Expert> experts, Set<Patent> patents,
			Set<Orgnization> orgnizations) {
		this.orgnization = orgnization;
		this.name = name;
		this.address = address;
		this.papers = papers;
		this.experts = experts;
		this.patents = patents;
		this.orgnizations = orgnizations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orgId", unique = true, nullable = false)
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "belongsTo")
	public Orgnization getOrgnization() {
		return this.orgnization;
	}

	public void setOrgnization(Orgnization orgnization) {
		this.orgnization = orgnization;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgnization")
	public Set<Paper> getPapers() {
		return this.papers;
	}

	public void setPapers(Set<Paper> papers) {
		this.papers = papers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgnization")
	public Set<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(Set<Expert> experts) {
		this.experts = experts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgnization")
	public Set<Patent> getPatents() {
		return this.patents;
	}

	public void setPatents(Set<Patent> patents) {
		this.patents = patents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgnization")
	public Set<Orgnization> getOrgnizations() {
		return this.orgnizations;
	}

	public void setOrgnizations(Set<Orgnization> orgnizations) {
		this.orgnizations = orgnizations;
	}

}
