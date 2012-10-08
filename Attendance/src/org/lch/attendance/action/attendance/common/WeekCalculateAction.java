package org.lch.attendance.action.attendance.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.service.DetailService;
import org.lch.attendance.util.StringUtils;
//@ParentPackage("myPackage")
public class WeekCalculateAction extends BaseAction {
	
	@javax.annotation.Resource
	private DetailService detailService;
	
	private Integer weekId;
	
	private String filename = StringUtils.formatUUID(java.util.UUID.randomUUID().toString());
	
	private String type = "application/vnd.ms-excel";
	private String disposition = "attachment;filename="+filename+".xls";
	private String size = "2048";
	
	@Action(results={@Result(name="success",type="stream",
			params={"contentType","application/vnd.ms-excel","contentDisposition","attachment;filename=${filename}","bufferSize","2048","inputName", "file"})})
	@Override
	public String execute() throws Exception {
		System.out.println(StringUtils.formatUUID(filename));
		saveASExcel(getWeekId(), filename);
		return SUCCESS;
	}
	
	public InputStream getFile(){
		return WeekCalculateAction.class.getResourceAsStream("/"+filename+".xls");
	}
	
	private void saveASExcel(Integer weekId,String filename){
		List<Detail> details = detailService.doStatistics(weekId);
		String path = Thread.currentThread().getContextClassLoader().getResource(File.separator).getPath();
		path = path.substring(1, path.length()-1) + "/";
		System.out.println(path);
		System.out.println(filename);
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					path+filename+".xls"));
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
			System.out.println("save as Excel...successed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getType() {
		return type;
	}
	public String getDisposition() {
		return disposition;
	}
	public String getSize() {
		return size;
	}

	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
