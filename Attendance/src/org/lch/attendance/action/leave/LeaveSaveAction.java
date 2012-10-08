package org.lch.attendance.action.leave;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.Message;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.LeaveService;
import org.lch.attendance.service.MessageService;
import org.lch.attendance.service.UserService;

public class LeaveSaveAction extends BaseAction {

	@javax.annotation.Resource
	private MessageService messageService;
	@javax.annotation.Resource
	private UserService userService;
	@javax.annotation.Resource
	private LeaveService leaveService;
	
	private Leave leave;
	
	private boolean shortMsg;




	


	@Action(value = "leave_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/leave/leave_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		User userSelf = userService.doFindByUserName(leave.getLeaveUser());
		User userDelegate = userService.doFindByUserName(leave.getLeaveDelegate());
		User userTeacher = userService.doFindByUserName(leave.getLeaveTeacher());
		
		leave.setUserSelf(userSelf);
		leave.setUserDelegate(userDelegate);
		leave.setUserTeacher(userTeacher);
		
		leave.setLeaveTime(new Date());
		leave.setLeaveStatus(false);
		leave.setLeaveFlag(false);
		leaveService.doCreate(leave);
		
		//给受理老师发送邮件
		String teacherEmail = userTeacher.getUserEmail();
		System.out.println(teacherEmail);
		String tContent = userSelf.getUserName()+" 同学发来请假申请，请处理。";
		sendEmail(teacherEmail,tContent,true);
		//同时给老师发站内信
		Message teacherMessage = new Message();
		teacherMessage.setMessageContent(tContent);
		teacherMessage.setMessageTime(new Date());
		teacherMessage.setMessageTitle("请假申请");
		teacherMessage.setUserReceiver(userTeacher);
		teacherMessage.setUserSender(userSelf);
		teacherMessage.setMessageStatus(false);
		messageService.doCreate(teacherMessage);
		
		//判断是否给老师发送提示短信
		if(shortMsg){
			sendMessage(userTeacher.getUserTel(),tContent);
		}
		//给代理人发邮件
		String studentEmail = userDelegate.getUserEmail();
		System.out.println(studentEmail);
		String sContent = userSelf.getUserName()+" 同学发来请假代理，请处理。";
		sendEmail(studentEmail,sContent,false);
		//同时给老师发站内信
		Message stuMessage = new Message();
		stuMessage.setMessageContent(sContent);
		stuMessage.setMessageTime(new Date());
		stuMessage.setMessageTitle("请假代理");
		stuMessage.setUserReceiver(userDelegate);
		stuMessage.setUserSender(userSelf);
		stuMessage.setMessageStatus(false);
		messageService.doCreate(stuMessage);
		
		return SUCCESS;
	}
	public void sendEmail(String receiverEmail,String content,boolean flag){
//		HtmlEmail email = new HtmlEmail();
		SimpleEmail email = new SimpleEmail();          
        email.setTLS(true);               
        email.setHostName("smtp.163.com");      
        email.setCharset("UTF-8");
        email.setAuthentication("attendance_admin@163.com", "efanhome");
               
        try        
        {       
            email.addTo(receiverEmail); //接收方       
            email.setFrom("attendance_admin@163.com");       //发送方 
            if(flag)
            	email.setSubject("请假申请");   //标题
            else
            	email.setSubject("请假代理");   //标题
            
            email.setMsg(content+"   本邮件由系统发出，请勿回复！");   //内容          
            email.send();        
            System.out.println("================>send email success.");       
        } catch (EmailException e) {       
            e.printStackTrace();       
        }        
	}
	
	public void sendMessage(String tel,String content){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", "efanhome"),
				new NameValuePair("Key", "1e9590e47a24e553d87c"),
				new NameValuePair("smsMob",tel),
				new NameValuePair("smsText",content)};
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:"+statusCode);
			for(Header h : headers)
			{
			System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("UTF-8"));
			System.out.println(result);
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
	}
	
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	public Leave getLeave() {
		return leave;
	}
	public boolean isShortMsg() {
		return shortMsg;
	}
	public void setShortMsg(boolean shortMsg) {
		this.shortMsg = shortMsg;
	}
	
	
}
