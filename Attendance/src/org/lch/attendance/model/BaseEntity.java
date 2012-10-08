package org.lch.attendance.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 8888332263579553197L;
	public static final String ID = "id";

	public abstract Integer getId();
	
	/**
	 * set entity id.
	 * 
	 * @param id
	 *            entity id.
	 */
	public abstract void setId(Integer id);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getId() == null) ? super.hashCode() : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
