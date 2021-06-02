package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.Schedule;
import com.harman.ebook.vaccination.covid.domain.VaccineInventorySchedule;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.LovRepository;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.DATE_FORMAT;

@Service
public class VaccineInventoryService {

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;

    @Autowired
    private LovRepository lovRepository;

    @Transactional
    public GenericResponseEntity insertIntoVaccineInventory(VaccineInventorySchedule vacInvSchedule) throws ParseException {
        List<VaccineInventory> inventoryList = new ArrayList<>();
        for(int i=0; i<vacInvSchedule.getSchedule().size(); i++) {
            VaccineInventory inventory = new VaccineInventory();
            inventory.setVacType(vacInvSchedule.getVacType());
            inventory.setLocation(vacInvSchedule.getLocation());
            Schedule schedule = vacInvSchedule.getSchedule().get(i);
            inventory.setDateOfAvailability(new SimpleDateFormat(DATE_FORMAT).parse(schedule.getDate()));
            inventory.setNoOfDoses(schedule.getNoOfDoses());
            inventory.setIsactive(Boolean.TRUE);
            inventoryList.add(inventory);

//            Lov lov = lovRepository.getLovByLovtypeIdIsActive(SLOT, true);
//            int noOfDoses = schedule.getNoOfDoses() / NO_OF_SLOTS;
//            for(int j=0; j<6; j++) {
//                SlotInfo slotInfo = new SlotInfo();
//                slotInfo.setNoOfDoses((short)noOfDoses);
//                slotInfo.setLocation(vacInvSchedule.getLocation());
//                slotInfo.setSlotNo(lov.get);
//            }

            //take slots from lov, create object and set slotInfo Entity and save it.


        }
        vaccineInventoryRepository.saveAll(inventoryList);

        //saveAll
        return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS, inventoryList.size());
    }
}
