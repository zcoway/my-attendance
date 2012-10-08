package org.lch.attendance.junit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.lch.attendance.domain.Detail;

//生成Excel的类

public class Test {
	public static void main(String args[]) {
		try   {
			String uri = "d:/myTest.xls";
			Workbook book  =  Workbook.getWorkbook( new  File( uri  ));
             //  获得第一个工作表对象
            Sheet sheet  =  book.getSheet( 0 );
            sheet.getColumns();
            sheet.getRows();
            System.out.println(sheet.getColumns());
            System.out.println(sheet.getRows());
             //  得到第一列第一行的单元格
            List<Detail> details = new ArrayList<Detail>();
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
                d.setDetailPub(Integer.parseInt(detailAffair==null?"0":detailAffair));
                d.setDetailClear(detailClear);
                
            }
            
//            Cell cell1  =  sheet.getCell(0,0);
//            Cell cell2  = sheet.getCell(1,0);
//            Cell cell3  = sheet.getCell(2,0);
//            Cell cell4  = sheet.getCell(3,0);
//            Cell cell5  = sheet.getCell(4,0);
//            Cell cell6  = sheet.getCell(5,0);
//            Cell cell7  = sheet.getCell(6,0);
//            Cell cell8  = sheet.getCell(7,0);
//            String result1  =  cell1.getContents();
//            String result2  =  cell2.getContents();
//            String result3  =  cell3.getContents();
//            String result4  =  cell4.getContents();
//            String result5  =  cell5.getContents();
//            String result6  =  cell6.getContents();
//            String result7  =  cell7.getContents();
//            String result8  =  cell8.getContents();
//            System.out.print(result1+",");
//            System.out.print(result2+",");
//            System.out.print(result3+",");
//            System.out.print(result4+",");
//            System.out.print(result5+",");
//            System.out.print(result6+",");
//            System.out.print(result7+",");
//            System.out.print(result8+",");
            book.close();
        }   catch  (Exception e)  {
            System.out.println(e);
        }
	}
}