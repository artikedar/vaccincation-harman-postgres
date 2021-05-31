package com.harman.ebook.vaccination.covid.response;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.STATUS_FLAG_FAILED;
import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.STATUS_FLAG_SUCCESS;
import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.STATUS_FLAG_WARNING;

import org.springframework.stereotype.Service;


@Service
public class ApplicationResponseService {
	
	public GenericResponseEntity genSuccessResponse( String message, Object data) {

	    // return generic response entity
	    GenericResponseEntity genericResponseEntity = new GenericResponseEntity();
	    Status s = new Status();
	    if(null == data)
	    	genericResponseEntity.setData("");
	    else
	    	genericResponseEntity.setData(data);
	   // genericResponseEntity.setData(data);
	    s.setStatusFlag(STATUS_FLAG_SUCCESS);
	    s.setStatusMsg(message);
	    genericResponseEntity.setStatus(s);
	    return genericResponseEntity;
	  }
	
	public GenericResponseEntity genFailureResponse( String message, Object data) {

	    // return generic response entity
	    GenericResponseEntity genericResponseEntity = new GenericResponseEntity();
	    Status s = new Status();
	    if(null == data)
	    	genericResponseEntity.setData("");
	    else
	    	genericResponseEntity.setData(data);
	    s.setStatusFlag(STATUS_FLAG_FAILED);
	    s.setStatusMsg(message);
	    genericResponseEntity.setStatus(s);
	    return genericResponseEntity;
	  }

}
