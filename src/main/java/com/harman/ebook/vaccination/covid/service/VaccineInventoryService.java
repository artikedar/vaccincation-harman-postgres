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

    //    @Transactional
    public void updateDoseAvailability(AppointmentRequest req) {

        Date bookingDate = DateUtil.getDate(req.getBookingDate());

        //fetch VaccineInventory according to request and update the number of doses
        VaccineInventory vaccineInventory = vaccineInventoryRepository.findVaccineInventoryByVacTypeAndLocationAndDateOfAvailability(req.getVacType(), req.getLocation(), bookingDate);
        Integer requestedDoses = req.getPersonIds().size();
        Integer noOfAvailableDoses = vaccineInventory.getNoOfAvailableDoses().intValue();
        Integer noOfBookedDoses = vaccineInventory.getNoOfBookedDoses().intValue();

        Integer noOfActualAvailableDoses = noOfAvailableDoses - requestedDoses;
        Integer noOfActualBookedDoses = noOfBookedDoses + requestedDoses;

        vaccineInventory.setNoOfAvailableDoses(noOfActualAvailableDoses.shortValue());
        vaccineInventory.setNoOfBookedDoses(noOfActualBookedDoses.shortValue());

        vaccineInventoryRepository.save(vaccineInventory);

        //fetch slotInfo according to vacInvId and slotNo
        SlotInfo slotInfoToBeModified = slotInfoRepository.findSlotInfosByVacInvIdAndSlotNo(vaccineInventory.getVacInvId(), req.getSlotNo());
        Integer noOfBookedDosesForSlot = slotInfoToBeModified.getNoOfBookedDoses().intValue();
        Integer noOfAvailableDosesForSlot = slotInfoToBeModified.getNoOfAvailableDoses().intValue();

        Integer noOfNewBookedDosesForSlot = noOfBookedDosesForSlot + requestedDoses;
        Integer noOfNewAvailableDosesForSlot = noOfAvailableDosesForSlot - requestedDoses;

        slotInfoToBeModified.setNoOfBookedDoses(noOfNewBookedDosesForSlot.shortValue());
        slotInfoToBeModified.setNoOfAvailableDoses(noOfNewAvailableDosesForSlot.shortValue());

        slotInfoRepository.save(slotInfoToBeModified);
    }

    public void updateDoseAvailability(EmployeeVaccAppointmentInfo employeeVaccAppointmentInfo) {

        //fetch VaccineInventory according to employeeVaccAppointmentInfo
        VaccineInventory vaccineInventory = vaccineInventoryRepository.findVaccineInventoryByVacTypeAndLocationAndDateOfAvailability(employeeVaccAppointmentInfo.getVacType(), employeeVaccAppointmentInfo.getLocation(), employeeVaccAppointmentInfo.getDateOfVaccination());
        Integer noOfAvailableDoses = vaccineInventory.getNoOfAvailableDoses().intValue();
        Integer noOfBookedDoses = vaccineInventory.getNoOfBookedDoses().intValue();

        Integer noOfActualAvailableDoses = noOfAvailableDoses + 1;
        Integer noOfActualBookedDoses = noOfBookedDoses - 1;

        vaccineInventory.setNoOfAvailableDoses(noOfActualAvailableDoses.shortValue());
        vaccineInventory.setNoOfBookedDoses(noOfActualBookedDoses.shortValue());

        vaccineInventoryRepository.save(vaccineInventory);

        //fetch slotInfo according to vacInvId and slotNo
        SlotInfo slotInfoToBeModified = slotInfoRepository.findSlotInfosByVacInvIdAndSlotNo(vaccineInventory.getVacInvId(), employeeVaccAppointmentInfo.getSlotNo());
        Integer noOfBookedDosesForSlot = slotInfoToBeModified.getNoOfBookedDoses().intValue();
        Integer noOfAvailableDosesForSlot = slotInfoToBeModified.getNoOfAvailableDoses().intValue();

        Integer noOfNewBookedDosesForSlot = noOfBookedDosesForSlot - 1;
        Integer noOfNewAvailableDosesForSlot = noOfAvailableDosesForSlot + 1;

        slotInfoToBeModified.setNoOfBookedDoses(noOfNewBookedDosesForSlot.shortValue());
        slotInfoToBeModified.setNoOfAvailableDoses(noOfNewAvailableDosesForSlot.shortValue());

        slotInfoRepository.save(slotInfoToBeModified);
    }
}

