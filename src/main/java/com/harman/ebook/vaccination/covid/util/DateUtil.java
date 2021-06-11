package com.harman.ebook.vaccination.covid.util;

import com.harman.ebook.vaccination.covid.response.ApplicationResponseService;
import java.sql.Timestamp;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import static com.harman.ebook.vaccination.covid.constants.VaccinationConstants.DATE_FORMAT;

public class DateUtil {

    @Autowired
    ApplicationResponseService applicationResponseService;

    public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS * 60;
    public final static long HOUR_MILLIS = MINUTE_MILLIS * 60;
    public final static long DAY_MILLIS = HOUR_MILLIS * 24;

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
     * @return
     */
    public static Date getUIDateFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String newFormatDate = formatter.format(date);
        return  formatDate(newFormatDate);

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

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int daysDiff(Timestamp startDate, Timestamp endDate) {
        if (ObjectUtils.isEmpty(startDate) || ObjectUtils.isEmpty(endDate)) return 0;

        return (int) ((endDate.getTime() / DAY_MILLIS) - (startDate.getTime() / DAY_MILLIS));
    }
}
