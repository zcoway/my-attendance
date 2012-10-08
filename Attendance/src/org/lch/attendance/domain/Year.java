package org.lch.attendance.domain;

import java.util.Date;

import javax.persistence.Entity;

import org.lch.attendance.model.BaseEntity;
@Entity
public class Year extends BaseEntity implements java.io.Serializable{

	private Integer yearId;
	
	private String yearName;
	
	private String yearDetail;
	
	private String yearDesc;
	
	private Date yearTime;
	
	
	@Override
	public Integer getId() {
		return getYearId();
	}

	@Override
	public void setId(Integer id) {
		setYearId(id);
	}

	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getYearDetail() {
		return yearDetail;
	}

	public void setYearDetail(String yearDetail) {
		this.yearDetail = yearDetail;
	}

	public String getYearDesc() {
		return yearDesc;
	}

	public void setYearDesc(String yearDesc) {
		this.yearDesc = yearDesc;
	}

	public Date getYearTime() {
		return yearTime;
	}

	public void setYearTime(Date yearTime) {
		this.yearTime = yearTime;
	}

}
