package org.lch.attendance.junit;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class SendMailTest {

	@Test
	public void test() {
//		HtmlEmail email = new HtmlEmail();
		SimpleEmail email = new SimpleEmail();          
        email.setTLS(true);               
        email.setHostName("smtp.163.com");      
        email.setCharset("UTF-8");
        email.setAuthentication("attendance_admin@163.com", "efanhome");
               
        try        
        {       
            email.addTo("efanhome@qq.com"); //接收方       
            email.setFrom("attendance_admin@163.com");       //发送方         
            email.setSubject("Java Mail Test");         //标题          
            email.setMsg("Just a simple send test .<br><h1>本邮件由系统发出1，请勿回复！</h1>");   //内容          
            email.send();        
            System.out.println("sendding success.");       
        } catch (EmailException e) {       
            e.printStackTrace();       
        }        
	}

}
