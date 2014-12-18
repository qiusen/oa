package com.dihaitech.oa.model;

import java.io.File;

/**
 * 流程布署模型
 * @author qiusen
 *
 */
public class ProcessModel extends BaseModel {

	private static final long serialVersionUID = -7434465889068202351L;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 流程文件
	 */
	private File processZipFile;
	
	private String processZipFileFileName;

	private String processZipFileContentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getProcessZipFile() {
		return processZipFile;
	}

	public void setProcessZipFile(File processZipFile) {
		this.processZipFile = processZipFile;
	}

	public String getProcessZipFileFileName() {
		return processZipFileFileName;
	}

	public void setProcessZipFileFileName(String processZipFileFileName) {
		this.processZipFileFileName = processZipFileFileName;
	}

	public String getProcessZipFileContentType() {
		return processZipFileContentType;
	}

	public void setProcessZipFileContentType(String processZipFileContentType) {
		this.processZipFileContentType = processZipFileContentType;
	}

}
