package com.harman.ebook.vaccination.covid.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Status {
	private String statusFlag;
	private String statusMsg;
	
	
	public Status() {
		super();
	}


	public Status(String statusFlag, String statusMsg) {
		super();
		this.statusFlag = statusFlag;
		this.statusMsg = statusMsg;
	}


	public String getStatusFlag() {
		return statusFlag;
	}


	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}


	public String getStatusMsg() {
		return statusMsg;
	}


	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	
	

}
