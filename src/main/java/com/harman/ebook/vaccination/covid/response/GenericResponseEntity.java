package com.harman.ebook.vaccination.covid.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericResponseEntity {
	
	private Object data;
	private Status status;
	public Object getData() {
		return data;
	}
	
	
	public GenericResponseEntity() {}


	public GenericResponseEntity(Object data, Status status) {
		super();
		this.data = data;
		this.status = status;
	}


	public void setData(Object data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
