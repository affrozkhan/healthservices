package com.health.controller.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractColumnDetails {

	@JsonIgnore
    @Column(name = "created_date")
    private Date createdDate;

	@JsonIgnore
    @Column(name = "updated_date")
    private Date updatedDate;
    
	@JsonIgnore
    @Column(name="created_by")
    private Long createdBy;
    
	@JsonIgnore
    @Column(name="updated_by")
    private Long updatedBy;
    
	@JsonIgnore
    @Column(name="active_status")
    private Long active_status;

 
}