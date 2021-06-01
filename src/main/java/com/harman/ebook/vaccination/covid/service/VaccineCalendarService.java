package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.domain.VaccineCalendarVO;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
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

@Service
public class VaccineCalendarService {
	@Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;


    public List<VaccineCalendarVO> getVaccineCalendarVO(String fromDateString, Short location) throws ParseException {
        Date date1=new SimpleDateFormat("yyyy-dd-mm").parse(fromDateString);
        Date tillDate = getNextDate(date1);
        List<VaccineInventory> vaccineInventoryList = vaccineInventoryRepository.findByLocationAndAndDateOfAvailabilityBetween(location, date1, tillDate);
        List<VaccineCalendarVO> voList = getVaccineCalendarVO(vaccineInventoryList);
        return voList;
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
