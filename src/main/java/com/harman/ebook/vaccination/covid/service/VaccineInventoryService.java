package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.AppointmentRequest;
import com.harman.ebook.vaccination.covid.domain.Schedule;
import com.harman.ebook.vaccination.covid.domain.VaccineInventorySchedule;
import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import com.harman.ebook.vaccination.covid.entity.Lov;
import com.harman.ebook.vaccination.covid.entity.SlotInfo;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.LovRepository;
import com.harman.ebook.vaccination.covid.repository.SlotInfoRepository;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.harman.ebook.vaccination.covid.constants.LovConstants.LOV_APP_STATUS_BOOKED;
import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.*;

@Service
public class VaccineInventoryService {

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;

    @Autowired
    private LovRepository lovRepository;

    @Autowired
    private SlotInfoRepository slotInfoRepository;

    @Transactional
    public GenericResponseEntity insertIntoVaccineInventory(VaccineInventorySchedule vacInvSchedule) throws ParseException {
        List<VaccineInventory> inventoryList = new ArrayList<>();
        List<Lov> lovList = lovRepository.getLovByLovtypeIdIsActive(SLOT, true);
        for (int i = 0; i < vacInvSchedule.getSchedule().size(); i++) {
            Schedule schedule = vacInvSchedule.getSchedule().get(i);
            VaccineInventory inventory = getVaccineInventory(vacInvSchedule, schedule);
            inventoryList.add(inventory);
        }
        List<VaccineInventory> savedInventoryList = vaccineInventoryRepository.saveAll(inventoryList);
        for (VaccineInventory vacInv : savedInventoryList) {
            List<SlotInfo> slotInfoList = getSlotInfoList(lovList, vacInv);
            slotInfoRepository.saveAll(slotInfoList);
        }
        return appResponseService.genSuccessResponse(VaccinationConstants.SAVED_RECORDS, inventoryList.size());
    }

    private List<SlotInfo> getSlotInfoList(List<Lov> lovList, VaccineInventory inventory) {
        List<SlotInfo> slotInfoList = new ArrayList<>();
        int noOfDosesPerSlot = inventory.getTotalNoOfDoses() / NO_OF_SLOTS;
        int remainingDoses = 0;
        if (inventory.getTotalNoOfDoses() % NO_OF_SLOTS != 0) {
            remainingDoses = inventory.getTotalNoOfDoses() - (noOfDosesPerSlot * NO_OF_SLOTS) + noOfDosesPerSlot;
        }
        for (int j = 0; j < lovList.size(); j++) {
            SlotInfo slotInfo = new SlotInfo();
            slotInfo.setSlotNo(lovList.get(j).getValueid().shortValue());
            slotInfo.setLocation(inventory.getLocation());
            slotInfo.setNoOfAvailableDoses((short) noOfDosesPerSlot);
            slotInfo.setTotalNoOfDoses((short) noOfDosesPerSlot);
            slotInfo.setNoOfBookedDoses((short) 0);
            slotInfo.setVacInvId(inventory.getVacInvId());
            if (remainingDoses != 0 && j == lovList.size() - 1) {
                slotInfo.setTotalNoOfDoses((short) remainingDoses);
                slotInfo.setNoOfAvailableDoses((short) remainingDoses);
            }
            slotInfoList.add(slotInfo);
        }
        return slotInfoList;
    }

    private VaccineInventory getVaccineInventory(VaccineInventorySchedule vacInvSchedule, Schedule schedule) {
        VaccineInventory inventory = new VaccineInventory();
        inventory.setVacType(vacInvSchedule.getVacType());
        inventory.setLocation(vacInvSchedule.getLocation());
        inventory.setDateOfAvailability(DateUtil.getDate(schedule.getDate()));
        inventory.setTotalNoOfDoses(schedule.getNoOfDoses());
        inventory.setNoOfAvailableDoses(schedule.getNoOfDoses());
        Short bookedDoses = 0;
        inventory.setNoOfBookedDoses(bookedDoses);
        inventory.setIsactive(Boolean.TRUE);
        return inventory;
    }

    /**
     *
     * @param appInfo
     */
    @Transactional
    public void updateDoseAvailability(EmployeeVaccAppointmentInfo appInfo) {

        //fetch VaccineInventory according to employeeVaccAppointmentInfo
        Date dateOfvac = appInfo.getDateOfVaccination();
        VaccineInventory vaccineInventory = vaccineInventoryRepository.findVaccineInventoryByVacTypeAndLocationAndDateOfAvailability(appInfo.getVacType(), appInfo.getLocation(),
            dateOfvac);
        Integer noOfAvailableDoses = vaccineInventory.getNoOfAvailableDoses().intValue();
        Integer noOfBookedDoses = vaccineInventory.getNoOfBookedDoses().intValue();

        //fetch slotInfo according to vacInvId and slotNo
        SlotInfo slotInfoToBeModified = slotInfoRepository.findSlotInfosByVacInvIdAndSlotNo(vaccineInventory.getVacInvId(), appInfo.getSlotNo());
        Integer noOfBookedDosesForSlot = slotInfoToBeModified.getNoOfBookedDoses().intValue();
        Integer noOfAvailableDosesForSlot = slotInfoToBeModified.getNoOfAvailableDoses().intValue();


        // update number of slots on booking
        if(appInfo.getStatus().shortValue() == LOV_APP_STATUS_BOOKED) {
            noOfAvailableDoses = noOfAvailableDoses - 1;
            noOfBookedDoses = noOfBookedDoses + 1;

            noOfAvailableDosesForSlot =noOfAvailableDosesForSlot -1;
            noOfBookedDosesForSlot = noOfBookedDosesForSlot +1;
        }else{
            noOfAvailableDoses = noOfAvailableDoses + 1;
            noOfBookedDoses = noOfBookedDoses - 1;

            noOfAvailableDosesForSlot =noOfAvailableDosesForSlot +1;
            noOfBookedDosesForSlot = noOfBookedDosesForSlot - 1;
        }

        //update inventory
        vaccineInventory.setNoOfAvailableDoses(noOfAvailableDoses.shortValue());
        vaccineInventory.setNoOfBookedDoses(noOfBookedDoses.shortValue());
        vaccineInventoryRepository.save(vaccineInventory);

        //update slots
        slotInfoToBeModified.setNoOfBookedDoses(noOfBookedDosesForSlot.shortValue());
        slotInfoToBeModified.setNoOfAvailableDoses(noOfAvailableDosesForSlot.shortValue());
        slotInfoRepository.save(slotInfoToBeModified);
    }
}

