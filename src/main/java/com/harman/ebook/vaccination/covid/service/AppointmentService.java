//package com.harman.ebook.vaccination.covid.service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import javax.transaction.Transactional;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
//import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
//import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
//import com.harman.ebook.vaccination.covid.entity.Person;
//import com.harman.ebook.vaccination.covid.exceptions.AppointmentExistException;
//import com.harman.ebook.vaccination.covid.exceptions.BasicAppointmentInputException;
//import com.harman.ebook.vaccination.covid.repository.MasterEmpRespository;
//import com.harman.ebook.vaccination.covid.repository.PersonRespository;
//
//
//@Service
//public class AppointmentService {
//
//	@Autowired
//	AppointmentRepository appointmentrepo;
//
//	@Autowired
//	PersonRespository personrepos;
//
//	@Autowired
//	MasterEmpRespository emprepos;
//
//	public Appointment createAppointment(AppointmentRequest req) {
//		try {
//		Appointment appointmentData = null;
//		List<Integer> personIds = req.getPersonIds();
//		if(null != personIds && personIds.size()>0 &&
//			null !=	req.getEmpMasterId() &&
//			null != req.getBookingDate() &&
//			null != req.getLocation() &&
//			null != req.getSlotNo()) {
//			Optional<List<Appointment>> fetched=appointmentrepo.findByEmpMasterId(
//					req.getEmpMasterId());
//			if(ObjectUtils.isEmpty(fetched)) {
//				boolean isSuccess=createBooking(req,personIds);
//				if(isSuccess) {
//					Appointment active=getActiveAppointment(req.getEmpMasterId());
//					return active;
//				}
//			}else {
//				for(Appointment appointment:fetched.get()) {
//					if(appointment.getIsactive()==true) {
//						throw new AppointmentExistException(VaccinationConstants.APPOINTMENT_EXIST_ALREADY);
//					}else {
//
//					}
//				}
//
//			}
//		}else {
//			throw new BasicAppointmentInputException(VaccinationConstants.INVALID_INPUT_ARGS_ALL_MANDATORY);
//		}
//
//		return null;
//		}catch(AppointmentExistException ex) {
//			ex.printStackTrace();
//			return null;
//		}catch(BasicAppointmentInputException ex) {
//			ex.printStackTrace();
//			return null;
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			return null;
//		}
//	}
//
//	private Appointment conversionAppointmentRequestToAppointment(AppointmentRequest req) {
//		Appointment appointment = new Appointment();
//		appointment.setEmpMasterId(req.getEmpMasterId());
//		appointment.setBookingDate(req.getBookingDate());
//		appointment.setLocation(req.getLocation());
//		appointment.setSlotNo(req.getSlotNo());
//		appointment.setIsactive(true);
//		appointment.setCreatedOn(new Date());
//		getEmployeeId(req);
//		appointment.setCreatedby((getEmployeeId(req)==null?null:getEmployeeId(req).toString()));
//		appointment.setModifiedOn(new Date());
//		appointment.setModifiedby((getEmployeeId(req)==null?null:getEmployeeId(req).toString()));
//		return appointment;
//	}
//
//	@Transactional
//	private boolean createBooking(AppointmentRequest req,
//			List<Integer> personIds) throws Exception {
//		boolean isCompleted=false;
//		Appointment appointmentData = null;
//		appointmentData = conversionAppointmentRequestToAppointment(req);
//		appointmentData.setAppointmentStatus(Short.parseShort("1"));
//
//		Appointment savedData = appointmentrepo.save(appointmentData);
//		appointmentrepo.flush();
//		Appointment verifiedapt=getActiveAppointment(req.getEmpMasterId());
//		//now set the appointmentid in person table
//		if(null != verifiedapt && verifiedapt.getIsactive()==true &&
//				verifiedapt.getAppointmentId()>0) {
//			for(Integer personid:personIds) {
//				Optional<Person> personOpt = personrepos.findById(personid);
//				if(personOpt.isPresent()) {
//					Person personFetched = personOpt.get();
//					personFetched.setAppointment(verifiedapt);
//					personrepos.save(personFetched);
//					personrepos.flush();
//					isCompleted=true;
//				}
//
//			}
//
//		}
//
//		return isCompleted;
//
//	}
//
//	private Appointment getActiveAppointment(Integer id) {
//		Optional<List<Appointment>> availableMeets=appointmentrepo.findByEmpMasterId(
//				id);
//		if(availableMeets.isPresent()) {
//			for(Appointment apt:availableMeets.get()) {
//				if(null != apt && apt.getIsactive()==true) {
//					return apt;
//				}
//			}
//		}
//		return null;
//	}
//
//	private Integer getEmployeeId(AppointmentRequest req) {
//		Optional<EmployeeMaster> emp=emprepos.findById(req.getEmpMasterId());
//		return emp.isPresent()?emp.get().getEmployeeId():null;
//
//	}
//
//
//
//}
