package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.VaccineCalendarVO;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.DATE_FORMAT;

@Service
public class VaccineCalendarService {
    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;

    /**
     *
     * @param fromDateString
     * @param location
     * @return list of vaccine inventories available for the next 5 days starting from fromDateString
     * @throws ParseException
     */
    public GenericResponseEntity getVaccineCalendarVO(String fromDateString, Short location) throws ParseException {
        Date fromDate=new SimpleDateFormat(DATE_FORMAT).parse(fromDateString);
        Date tillDate = getNextDate(fromDate);
//        List<VaccineInventory> vaccineInventoryList = vaccineInventoryRepository.findByLocationAndAndDateOfAvailabilityBetween(location, fromDate, tillDate);
        List<VaccineInventory> vaccineInventoryList = vaccineInventoryRepository.findVaccineInventoryByLocation(location);
        return appResponseService.genSuccessResponse(VaccinationConstants.RECORD_FOUNDS, getVaccineCalendarVO(vaccineInventoryList));
    }

    private List<VaccineCalendarVO> getVaccineCalendarVO(List<VaccineInventory> vaccineInventoryList) {
        List<VaccineCalendarVO> voList = new ArrayList<>();
        for (VaccineInventory vacInv: vaccineInventoryList)
        {
            VaccineCalendarVO vaccineCalendarVO = new VaccineCalendarVO();
            vaccineCalendarVO.setVacType(vacInv.getVacType());
            vaccineCalendarVO.setLocation(vacInv.getLocation());
            vaccineCalendarVO.setNoOfDoses(vacInv.getNoOfDoses());
            vaccineCalendarVO.setDateOfAvailability(vacInv.getDateOfAvailability());
            vaccineCalendarVO.setNoOfDoses(vacInv.getNoOfDoses());
            voList.add(vaccineCalendarVO);
        };
        return voList;
    }

    /**
     * get next date of a date
     * @param fromDate
     * @return
     */
    public Date getNextDate(Date fromDate) {
        return DateUtils.addDays(fromDate,5);
    }
}
