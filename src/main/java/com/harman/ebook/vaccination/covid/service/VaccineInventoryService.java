package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.Schedule;
import com.harman.ebook.vaccination.covid.domain.VaccineInventorySchedule;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.DATE_FORMAT;

@Service
public class VaccineInventoryService {

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;

    @Transactional
    public GenericResponseEntity insertIntoVaccineInventory(VaccineInventorySchedule vacInvSchedule) throws ParseException {
        VaccineInventory inventory = new VaccineInventory();
        inventory.setLocation(vacInvSchedule.getLocation());
        inventory.setVacType(vacInvSchedule.getVacType());
        for(int i=0; i<vacInvSchedule.getSchedule().size(); i++) {
            Schedule schedule = vacInvSchedule.getSchedule().get(i);
            inventory.setDateOfAvailability(new SimpleDateFormat(DATE_FORMAT).parse(schedule.getDate()));
            inventory.setNoOfDoses(schedule.getNoOfDoses());
            inventory.setIsactive(Boolean.TRUE);
            vaccineInventoryRepository.save(inventory);
            //take slots from lov, create object and set slotInfo Entity and save it.
        }

        //saveAll
        return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS, vacInvSchedule.getSchedule().size());
    }
}
