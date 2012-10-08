package org.lch.attendance.action.attendance.common;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.service.DetailService;

public class DetailImportAction extends BaseAction {

	@javax.annotation.Resource
	private DetailService detailService; 
	
	private File file;
	
	private String fileFileName;
	private String fileContentType;
	
	private Integer weekId;
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
		
		detailService.doImport(getWeekId(), filepath);
		return null;
	}
	
	
	
	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
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
