//package com.harman.ebook.vaccination.covid.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
//import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
//import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
//import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
//import com.harman.ebook.vaccination.covid.service.AppointmentService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping(value = "${spring.data.rest.basePath}", produces = MediaType.APPLICATION_JSON_VALUE)
//@Slf4j
//public class AppointmentController {
//
//	@Autowired
//	ApplicationResponseService appResponseService;
//
//	@Autowired
//	AppointmentService appointmentService;
//
//	/**
//	 *
//	 * @param req
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/api/appointment/schedule")
//	public GenericResponseEntity createAppointment(@RequestBody AppointmentRequest req) throws Exception {
//		//Appointment appointment = appointmentService.createAppointment(req);
//		//if(null != appointment)
//			return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS,"appointment");
//		//else
//			//return appResponseService.genFailureResponse(VaccinationConstants.STATUS_FLAG_FAILED, null);
//
//	}
//
//	/**
//	 *
//	 * @param apppointmentid
//	 * @param req
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/api/vaccine/appointment/{apppointmentid}/reschedule")
//	public GenericResponseEntity createAppointment(@PathVariable Integer apppointmentid,
//			@RequestBody AppointmentRequest req) throws Exception {
//		//Appointment appointment = appointmentService.createAppointment(req);
//		//if(null != appointment)
//		//new booking that should be saved
//		// fetch old booking for that appId, change status to cancel
//
//
//		return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS,"appointment");
//		//else
//		//return appResponseService.genFailureResponse(VaccinationConstants.STATUS_FLAG_FAILED, null);
//	}
//
//	/**
//	 *
//	 * @param apppointmentid
//	 *
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/api/vaccine/appointment/{apppointmentid}/cancel")
//	public GenericResponseEntity createAppointment(@PathVariable Integer apppointmentid) throws Exception {
//		//Appointment appointment = appointmentService.createAppointment(req);
//		//if(null != appointment)
//		return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS,"appointment");
//		//else
//		//return appResponseService.genFailureResponse(VaccinationConstants.STATUS_FLAG_FAILED, null);
//	}
//
//}
