package com.harman.ebook.vaccination.covid.service;

import com.harman.ebook.vaccination.covid.constants.VaccinationConstants;
import com.harman.ebook.vaccination.covid.domain.SlotInfoVO;
import com.harman.ebook.vaccination.covid.domain.VaccineCalendarVO;
import com.harman.ebook.vaccination.covid.entity.SlotInfo;
import com.harman.ebook.vaccination.covid.entity.VaccineInventory;
import com.harman.ebook.vaccination.covid.repository.SlotInfoRepository;
import com.harman.ebook.vaccination.covid.repository.VaccineInventoryRepository;
import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import com.harman.ebook.vaccination.covid.response.GenericResponseEntity;
import com.harman.ebook.vaccination.covid.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.NEXT_DAYS;

@Service
public class VaccineCalendarService {

    @Autowired
    private ApplicationResponseService appResponseService;

    @Autowired
    private VaccineInventoryRepository vaccineInventoryRepository;

    @Autowired
    private SlotInfoRepository slotInfoRepository;

    /**
     *
     * @param fromDateString
     * @param location
     * @return list of vaccine inventories available for the next 5 days starting from fromDateString
     * @throws ParseException
     */
    public GenericResponseEntity getVaccineCalendarVO(String fromDateString, Short location) throws ParseException {
        Date fromDate = DateUtil.getDate(fromDateString);
        Date tillDate = DateUtil.getNextDate(fromDate, NEXT_DAYS);
        List<VaccineInventory> vaccineInventoryList = vaccineInventoryRepository.findByLocationAndDateOfAvailabilityBetweenAndIsActiveAndOrderByDateOfAvailability(location, fromDate, tillDate, true);
        return appResponseService.genSuccessResponse(VaccinationConstants.RECORD_FOUNDS, getVaccineCalendarVO(vaccineInventoryList));
    }

    private List<VaccineCalendarVO> getVaccineCalendarVO(List<VaccineInventory> vaccineInventoryList) {
        List<VaccineCalendarVO> voList = new ArrayList<>();
        for (VaccineInventory vacInv: vaccineInventoryList) {
            VaccineCalendarVO vaccineCalendarVO = new VaccineCalendarVO();
            vaccineCalendarVO.setVacType(vacInv.getVacType());
            vaccineCalendarVO.setLocation(vacInv.getLocation());
            vaccineCalendarVO.setNoOfDoses(vacInv.getTotalNoOfDoses());
            vaccineCalendarVO.setDateOfAvailability(DateUtil.getDateString(vacInv.getDateOfAvailability()));
            vaccineCalendarVO.setNoOfAvailableDoses(vacInv.getNoOfAvailableDoses());
            vaccineCalendarVO.setNoOfBookedDoses(vacInv.getNoOfBookedDoses());
            List<SlotInfo> slotInfoList = slotInfoRepository.findSlotInfosByVacInvId(vacInv.getVacInvId());
            List<SlotInfoVO> slotInfoVOList = new ArrayList<>();
            for(SlotInfo slotInfo : slotInfoList) {
                SlotInfoVO slotInfoVO = getSlotInfoVO(slotInfo);
                slotInfoVOList.add(slotInfoVO);
            }
            vaccineCalendarVO.setSlotInfoList(slotInfoVOList);
            voList.add(vaccineCalendarVO);
        };
        voList.stream().sorted();
        Collections.sort(voList,new Comparator<VaccineCalendarVO>() {
            @Override
            public int compare(VaccineCalendarVO s1, VaccineCalendarVO s2) {
                return s1.getDateOfAvailability().compareToIgnoreCase(s2.getDateOfAvailability());
            }
        });
        return voList;
    }

    private SlotInfoVO getSlotInfoVO(SlotInfo slotInfo) {
        SlotInfoVO slotInfoVO = new SlotInfoVO();
        slotInfoVO.setSlotInfoId(slotInfo.getSlotInfoId());
        slotInfoVO.setSlotNo(slotInfo.getSlotNo());
        slotInfoVO.setLocation(slotInfo.getLocation());
        slotInfoVO.setNoOfAvailableDoses(slotInfo.getNoOfAvailableDoses());
        slotInfoVO.setTotalNoOfDoses(slotInfo.getTotalNoOfDoses());
        slotInfoVO.setNoOfBookedDoses(slotInfo.getNoOfBookedDoses());
        return slotInfoVO;
    }
}
