package com.health.controller.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractColumnDetails {

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@Column(name = "updated_date")
	private Date updatedDate;

	@JsonIgnore
	@Column(name="created_by", updatable = false)
	private Long createdBy;

	@JsonIgnore
	@Column(name="updated_by")
	private Long updatedBy;

	@JsonIgnore
	@Column(name="active_status")
	private Long activeStatus;

	/*public AbstractColumnDetails(){}

	public AbstractColumnDetails(Date createdDate, Date updatedDate, Long createdBy, Long updatedBy,
			Long activeStatus) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.activeStatus = activeStatus;
	}*/


	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

}