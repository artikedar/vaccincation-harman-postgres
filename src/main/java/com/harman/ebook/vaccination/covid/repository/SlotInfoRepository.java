package com.harman.ebook.vaccination.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.SlotInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotInfoRepository extends JpaRepository<SlotInfo, Integer>{

    SlotInfo findSlotInfosByVacInvIdAndSlotNo(Integer vacInvId, Short slotNo);

    List<SlotInfo> findSlotInfosByVacInvId(Integer vacInvId);
    List<SlotInfo> findSlotInfosByVacInvIdOrderBySlotNoAsc(Integer vacInvId);

}
