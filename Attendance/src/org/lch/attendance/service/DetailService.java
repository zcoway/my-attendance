package org.lch.attendance.service;

import java.util.List;

import org.lch.attendance.domain.Detail;

public interface DetailService extends GenericService<Detail> {
	public List<Detail> doStatistics(Integer weekId);

	public int doCount(Integer weekId);
	
	public int doCount();
	
	public void doImport(Integer weekId,String filepath);
}
