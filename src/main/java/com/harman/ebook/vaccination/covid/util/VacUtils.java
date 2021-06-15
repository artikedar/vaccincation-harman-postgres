package com.harman.ebook.vaccination.covid.util;

import com.harman.ebook.vaccination.covid.entity.Lov;
import java.util.List;
import org.springframework.util.ObjectUtils;

/**
 * @author Bhagwat Rokade
 * @project Harman Vaccination
 * @created 6/16/2021
 */
public class VacUtils {

  /**
   *
   * @param lovList
   * @return
   */
  public static String getLovValue(List<Lov> lovList,int valueId){
    Lov lov = lovList.stream().filter(l -> l.getValueid() == valueId).findFirst().orElse(null);
    if (!ObjectUtils.isEmpty(lov)) {
      return lov.getValue();
    }
    return  "";
  }

}
