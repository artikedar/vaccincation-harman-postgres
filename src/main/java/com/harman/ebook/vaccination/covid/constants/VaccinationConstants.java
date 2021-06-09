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
public final class VaccinationConstants {
  public static final String STATUS_FLAG_FAILED = "failed";
  public static final String STATUS_FLAG_SUCCESS = "success";
  public static final String STATUS_FLAG_WARNING = "warning";
  public static final String RECORD_FOUNDS="Entity record found";
  public static final String RECORD_NOT_FOUNDS="Entity record not found";
  public static final String INVALID_USER="invalid user";
  public static final String VALID_USER="valid user";
  public final static String DATE_FORMAT = "yyyy-MM-dd";
  public static final String SAVED_RECORDS = "Successfully Saved Records";
  public static final String INVALID_INPUT_ARGS_ALL_MANDATORY = "Invalid input arguments all mandatory fields";
  public static final String APPOINTMENT_EXIST_ALREADY = "You can't create more than one appointment";
  public static final Short SLOT = 4;
  public static final Short NO_OF_SLOTS = 6;
  public static final int NEXT_DAYS = 14;
  public static final String SESSION_EXPIRED = "User Session Expired!";
  public static final String USER_NOT_LOGGED_IN = "User not logged in";
}
