package org.lch.attendance.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.DetailDao;
import org.lch.attendance.dao.UserDao;
import org.lch.attendance.dao.WeekDao;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.User;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.DetailService;
import org.springframework.stereotype.Service;
@Service("detailService")
public class DetailServiceImpl extends GenericServiceImpl<Detail> implements
		DetailService {

	@javax.annotation.Resource
	private DetailDao detailDao;
	@javax.annotation.Resource
	private UserDao userDao;
	@javax.annotation.Resource
	private WeekDao weekDao;
	
	@Override
	public QueryResult<Detail> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		QueryResult<Detail> qr=detailDao.getScrollData(Detail.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}
	
	public List<Detail> doStatistics(Integer weekId){
		List list = detailDao.getStatisticsResult(weekId);
		Iterator it = list.iterator();
		List<Detail> details = new ArrayList<Detail>();
		while(it.hasNext()){
			Object[] a = (Object[]) it.next();
			Detail d = new Detail();
			d.setUserName((String)a[0]);
			d.setDetailLate(Integer.parseInt(a[1]==null?"0":a[1].toString()));
			d.setDetailEarly(Integer.parseInt(a[2]==null?"0":a[2].toString()));
			d.setDetailQuit(Integer.parseInt(a[3]==null?"0":a[3].toString()));
			d.setDetailIll(Integer.parseInt(a[4]==null?"0":a[4].toString()));
			d.setDetailAffair(Integer.parseInt(a[5]==null?"0":a[5].toString()));
			d.setDetailPub(Integer.parseInt(a[6]==null?"0":a[6].toString()));
			if(isClearAttendance(weekId, d.getUserName())){
				d.setDetailClear("是");
			}else{
				d.setDetailClear("否");
			}
			details.add(d);
		}
		return details;
	}
	
	public boolean isClearAttendance(Integer weekId,String userName){
		List list = detailDao.findWithParams(weekId,userName);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).equals("否")){
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int doCount(Integer weekId) {
		return detailDao.countAttUser(weekId);
	}

	@Override
	public int doCount() {
		return detailDao.countAttUser();
	}

	
	public void doImport(Integer weekId,String filepath){
		try{
            Week week = weekDao.findById(weekId);
			Workbook book  =  Workbook.getWorkbook( new  File( filepath  ));
             //  获得第一个工作表对象
            Sheet sheet  =  book.getSheet(0);
            for(int i=0;i<sheet.getRows();i++){
            	
                String userName  =  sheet.getCell(0,i).getContents();
                String detailLate  = sheet.getCell(1,i).getContents();
                String detailEarly  = sheet.getCell(2,i).getContents();
                String detailQuit = sheet.getCell(3,i).getContents();
                String detailIll = sheet.getCell(4,i).getContents();
                String detailAffair = sheet.getCell(5,i).getContents();
                String detailPub = sheet.getCell(6,i).getContents();
                String detailClear = sheet.getCell(7,i).getContents();
                
                Detail d = new Detail();
                d.setUserName(userName);
                d.setDetailLate(Integer.parseInt(detailLate==null || (detailLate.trim().length()==0)?"0":detailLate));
                d.setDetailEarly(Integer.parseInt(detailEarly==null || (detailEarly.trim().length()==0)?"0":detailEarly));
                d.setDetailQuit(Integer.parseInt(detailQuit==null || (detailQuit.trim().length()==0)?"0":detailQuit));
                d.setDetailIll(Integer.parseInt(detailIll==null || (detailIll.trim().length()==0)?"0":detailIll));
                d.setDetailAffair(Integer.parseInt(detailAffair==null || (detailAffair.trim().length()==0)?"0":detailAffair));
                d.setDetailPub(Integer.parseInt(detailPub==null || (detailPub.trim().length()==0)?"0":detailPub));
                d.setDetailClear(detailClear);
                
                User user = userDao.findByName(userName);
                d.setUser(user);
                d.setWeek(week);
                d.setDetailTime(new Date());
                
                detailDao.create(d);
            }
            book.close();
        }   catch  (Exception e)  {
            System.out.println(e);
        }
	}
}
