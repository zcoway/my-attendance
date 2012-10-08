package org.lch.attendance.junit;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.dao.UserDao;
import org.lch.attendance.dao.WeekDao;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.User;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.DetailService;
import org.lch.attendance.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DetailSatisTest {

	private static GenericDao genericDao;
	private static UserDao userDao;
	private static WeekDao weekDao;
	private static DetailService detailService;
//	final String sql= "select pk_user_id,sum(detail_late) detail_late,sum(detail_early) detail_early," +
//			"sum(detail_quit) detail_quit,sum(detail_ill) detail_ill,sum(detail_affair) detail_affair," +
//			"sum(detail_pub) detail_pub from att_detail group by pk_user_id,pk_week_id having pk_week_id=?";
//	final String hql = "select d.user.userName,sum(d.detailLate),sum(d.detailEarly),sum(d.detailQuit)," +
//			"sum(d.detailIll),sum(d.detailAffair),sum(d.detailPub) " +
//			"from Detail d group by d.user.userName,d.week.weekId having d.week.weekId=?";
	final String hql = "select d.user.userName,sum(d.detailLate),sum(d.detailEarly),sum(d.detailQuit)," +
			"sum(d.detailIll),sum(d.detailAffair),sum(d.detailPub) " +
			"from Detail d group by d.user.userName";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		genericDao = (GenericDao)act.getBean("genericDao");
		detailService = (DetailService)act.getBean("detailService");
		userDao = (UserDao)act.getBean("userDao");
		weekDao = (WeekDao)act.getBean("weekDao");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void execute() {
		
//		List<Detail> list = genericDao.getSuperHibernateTemplate().executeFind(new HibernateCallback(){
//			public Object doInHibernate(Session arg0)
//					throws HibernateException, SQLException {
//				Session session = genericDao.getHibernateSession();
//				SQLQuery query = session.createSQLQuery(sql);    
//			    query.setInteger(0, 2);  
//			    return query.list();  
//			}
//		});
//		Integer.v
		Integer weekId = 2;
		List list = genericDao.getStatisticsResult(weekId);
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
		String[] excludes = {"week","user"};
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", details);//total键 存放总记录数
//		jsonMap.put("rows", roles);//rows键 存放每页记录 list 
		
		JSONObject result = new JSONObject();
//		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		result = JSONObject.fromObject(jsonMap);
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
//		List<Detail> details = new ArrayList<Detail>();
//		details.add(new Detail());
//		System.out.println(list);
//		System.out.println(list);
//		Map<String,Integer> params = new HashMap<String,Integer>();
//		params.put("pk_week_id", 2);
//		List lits = genericDao.getSuperHibernateTemplate().fin
	}
	
	@Test
	public void test(){
		String hql = "select d.detailClear from Detail d where d.week.weekId=? AND d.user.userName=?";
		List list = genericDao.getSuperHibernateTemplate().find(hql, 2,"admin");
		System.out.println(list);
	}
	public boolean isClearAttendance(Integer weekId,String userName){
		String hql = "select d.detailClear from Detail d where d.week.weekId=? AND d.user.userName=?";
		List list = genericDao.getSuperHibernateTemplate().find(hql, weekId,userName);
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals("否")){
				return false;
			}
		}
		return true;
	}
	
	@Test 
	public void test2(){
		Integer weekId = 2;
		List<Detail> details = detailService.doStatistics(weekId);
		
		String[] excludes = {"week","user"};
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", details);//total键 存放总记录数
		
		JSONObject result = new JSONObject();
//		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
//		JsonConfig jc = new JsonConfig();
//		jc.setExcludes(excludes);
		result = JSONObject.fromObject(jsonMap);
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
	}
	
	@Test 
	public void test3(){
		Integer weekId = 2;
		List<Detail> details = detailService.doStatistics(weekId);
		String uuid = java.util.UUID.randomUUID().toString();
		System.out.println(StringUtils.formatUUID(uuid));
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					StringUtils.formatUUID(uuid)+".xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			WritableCellFormat format1 = new WritableCellFormat();
			// 把水平对齐方式指定为居中
			format1.setAlignment(jxl.format.Alignment.CENTRE);
			// 把垂直对齐方式指定为居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			Label label1 = new Label(0, 0, "姓名",format1);
			Label label2 = new Label(1, 0, "迟到",format1);
			Label label3 = new Label(2, 0, "早退",format1);
			Label label4 = new Label(3, 0, "旷课",format1);
			Label label5 = new Label(4, 0, "事假",format1);
			Label label6 = new Label(5, 0, "病假",format1);
			Label label7 = new Label(6, 0, "公假",format1);
			Label label8 = new Label(7, 0, "销假情况",format1);

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			int num=1;
			for(Detail d : details){
				Label l1 = new Label(0, num, d.getUserName(),format1);
				jxl.write.Number number1 = new jxl.write.Number(1, num, d.getDetailLate(),format1);
				jxl.write.Number number2 = new jxl.write.Number(2, num, d.getDetailEarly(),format1);
				jxl.write.Number number3 = new jxl.write.Number(3, num, d.getDetailQuit(),format1);
				jxl.write.Number number4 = new jxl.write.Number(4, num, d.getDetailIll(),format1);
				jxl.write.Number number5 = new jxl.write.Number(5, num, d.getDetailAffair(),format1);
				jxl.write.Number number6 = new jxl.write.Number(6, num, d.getDetailPub(),format1);
				Label l8 = new Label(7, num, d.getDetailClear(),format1);
				
				sheet.addCell(l1);
				sheet.addCell(l8);
				sheet.addCell(number1);
				sheet.addCell(number2);
				sheet.addCell(number3);
				sheet.addCell(number4);
				sheet.addCell(number5);
				sheet.addCell(number6);
				num++;
			}
			book.write();
			book.close();
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@Test
	public void test4(){
		try   {
			String filepath = "d:/myTest.xls";
			Workbook book  =  Workbook.getWorkbook( new  File( filepath  ));
             //  获得第一个工作表对象
            Sheet sheet  =  book.getSheet( 0 );
            sheet.getColumns();
            sheet.getRows();
            System.out.println(sheet.getColumns());
            System.out.println(sheet.getRows());
             //  得到第一列第一行的单元格
            Integer weekId = 2;
            Week week = weekDao.findById(weekId);
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
                d.setDetailLate(Integer.parseInt(detailLate==null?"0":detailLate));
                d.setDetailEarly(Integer.parseInt(detailEarly==null?"0":detailEarly));
                d.setDetailQuit(Integer.parseInt(detailQuit==null?"0":detailQuit));
                d.setDetailIll(Integer.parseInt(detailIll==null?"0":detailIll));
                d.setDetailAffair(Integer.parseInt(detailAffair==null?"0":detailAffair));
                d.setDetailPub(Integer.parseInt(detailPub==null?"0":detailPub));
                d.setDetailClear(detailClear);
                
                User user = userDao.findByName(userName);
                d.setUser(user);
                d.setWeek(week);
                d.setDetailTime(new Date());
                
                detailService.doCreate(d);
            }
            book.close();
        }   catch  (Exception e)  {
            System.out.println(e);
        }
	}
}
