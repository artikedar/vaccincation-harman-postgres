package com.harman.ebook.vaccination.covid.constants;


import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Bhagwat Rokade
 * @project HarmanVaccination
 * @created 5/30/2021
 */
@Getter
@NoArgsConstructor
public final class LovConstants {
  public static final Short LOV_APP_STATUS_BOOKED = 1;
  public static final Short LOV_APP_STATUS_CANCELED = 2;
  public static final Short LOV_APP_STATUS_RESCHEDULED = 3;
  public static final Short LOV_APP_STATUS_COMPLETED = 4;
  public static final Short LOV_TYPE_STATUS = 4;
}
