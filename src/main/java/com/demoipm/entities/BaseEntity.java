package com.demoipm.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity {

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	@CreationTimestamp
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "is_delete")
	private boolean isDelete;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}