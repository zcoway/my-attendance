package org.lch.attendance.service;

import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.Week;

public interface WeekService extends GenericService<Week>{

	Detail doFindDetailByWeekId(Integer weekId);
	
//	void saveASExcel(Integer weekId,String filename);
}
