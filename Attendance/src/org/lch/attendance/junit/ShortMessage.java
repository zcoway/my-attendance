package org.lch.attendance.junit;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class ShortMessage {

	@Test
	public void test() {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", "efanhome"),
				new NameValuePair("Key", "1e9590e47a24e553d87c"),
				new NameValuePair("smsMob","18950182619,13559248593"),
				new NameValuePair("smsText","来自[高校考勤考核管理系统]，请勿回复！")};
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

}
