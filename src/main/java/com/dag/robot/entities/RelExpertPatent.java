package com.dag.robot.entities;

// Generated 2015-5-20 21:09:50 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RelExpertPatent generated by hbm2java
 */
@Entity
@Table(name = "rel_expert_patent", catalog = "db_expert_robot")
public class RelExpertPatent implements java.io.Serializable {

	private RelExpertPatentId id;
	private Expert expert;
	private Patent patent;
	private int authorOrder;

	public RelExpertPatent() {
	}

	public RelExpertPatent(RelExpertPatentId id, Expert expert, Patent patent,
			int authorOrder) {
		this.id = id;
		this.expert = expert;
		this.patent = patent;
		this.authorOrder = authorOrder;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "expertId", column = @Column(name = "expertId", nullable = false)),
			@AttributeOverride(name = "patentId", column = @Column(name = "patentId", nullable = false)) })
	public RelExpertPatentId getId() {
		return this.id;
	}

	public void setId(RelExpertPatentId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expertId", nullable = false, insertable = false, updatable = false)
	public Expert getExpert() {
		return this.expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patentId", nullable = false, insertable = false, updatable = false)
	public Patent getPatent() {
		return this.patent;
	}

	public void setPatent(Patent patent) {
		this.patent = patent;
	}

	@Column(name = "authorOrder", nullable = false)
	public int getAuthorOrder() {
		return this.authorOrder;
	}

	public void setAuthorOrder(int authorOrder) {
		this.authorOrder = authorOrder;
	}

}
