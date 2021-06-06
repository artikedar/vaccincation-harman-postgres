package com.harman.ebook.vaccination.covid.util;

import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.DATE_FORMAT;

public class DateUtil {

    @Autowired
    ApplicationResponseService applicationResponseService;
    /**
     * get next date of a date
     * @param fromDate
     * @return
     */
    public static Date getNextDate(Date fromDate, int nextDays) {
        return DateUtils.addDays(fromDate,nextDays);
    }

    /**
     * convert NPD date to UI date
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr){
        Date date = null;
        try {
            date =new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * convert NPD date to UI date
     * @param dateStr
     * @return
     */
    public static Date getUIDateFormat(Date date){
        Date uiDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        uiDate = getDate(formatter.format(date));
        return uiDate;
    }

    /**
     * convert NPD date to UI date
     * @param dateStr
     * @return
     */
    public static String getDateString(Date date){
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    /**
     * get new date fomat
     * @param doj
     * @return
     */
    public static Date formatDate(String doj  ){
        Date date = null;
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(doj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
