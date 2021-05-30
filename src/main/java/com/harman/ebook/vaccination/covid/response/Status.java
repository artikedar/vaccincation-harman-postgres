package com.harman.ebook.vaccination.covid.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//@Getter
//@Setter
@NoArgsConstructor
public class Status {
	String statusFlag;
	String statusMsg;
	
	public Status() {}

	public Status(String statusFlag, String statusMsg) {
		super();
		this.statusFlag = statusFlag;
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "Status [statusFlag=" + statusFlag + ", statusMsg=" + statusMsg + "]";
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
