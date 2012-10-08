package org.lch.attendance.dao.impl;

import org.lch.attendance.dao.LeaveDao;
import org.lch.attendance.domain.Leave;
import org.springframework.stereotype.Repository;
@Repository("leaveDao")
public class LeaveDaoImpl extends GenericDaoImpl<Leave> implements LeaveDao {
	
}
