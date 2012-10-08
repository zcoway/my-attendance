package org.lch.attendance.action.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.service.UserService;

import com.opensymphony.xwork2.ActionContext;
@ParentPackage("default")
public class UserImportAction extends BaseAction {

	@javax.annotation.Resource
	private UserService userService; 
	
	private File file;
	
	private String fileFileName;
	private String fileContentType;
	
	private Map<String,Object> result;
	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		System.out.println("##"+file.getName());
		System.out.println("@@"+fileFileName);
		System.out.println("##"+fileContentType);
		String filepath = getSaveDir()+"/"+fileFileName;
		File targetFile = new File(filepath);
		if(!targetFile.getParentFile().exists())
			targetFile.getParentFile().mkdirs();
		System.out.println("filepath"+filepath);
		file.renameTo(targetFile);
//		FileUtils.copyFile(file, targetFile);
		result = new HashMap<String, Object>();
		if(userService.doImport(filepath)){
			result.put("success", Boolean.TRUE);
		}else{
			result.put("success", Boolean.FALSE);
		}
//		userService.doImport(filepath);
//		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		return SUCCESS;
	}
	

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private String getSaveDir(){
		String realpath = ServletActionContext.getServletContext().getRealPath("/uploads");
		System.out.println("realpath:"+realpath);
		return realpath;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
}
