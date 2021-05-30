package com.harman.ebook.vaccination.covid.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericResponseEntity {
	
	Object data;
	Status status;
	
	
	
	public GenericResponseEntity() {}

	public GenericResponseEntity(Object data, Long count, Status status) {
		this.data = data;
		this.status = status;
	}

	@Override
	public String toString() {
		return "GenericResponseEntity [data=" + data + ", status=" + status + "]";
	}

	public Object getData() {
		return data;
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
