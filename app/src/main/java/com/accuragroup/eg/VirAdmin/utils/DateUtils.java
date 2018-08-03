package com.accuragroup.eg.VirAdmin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Karam on 2/22/2015.
 */
public class DateUtils {
    public static Calendar convertToCalendar(String strDate, String strFormat) {
        Calendar calendar = Calendar.getInstance();
        try {
            final DateFormat df = new SimpleDateFormat(strFormat);
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static String convertToString(Calendar calendar, String strFormat) {
        String strDate;
        try {
            SimpleDateFormat format = new SimpleDateFormat(strFormat);
            strDate = format.format(calendar.getTime());
        } catch (Exception e) {
            strDate = null;
            e.printStackTrace();
        }

        return strDate;
    }

    public static long parsedDateFormate(String text) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

        return sdf.parse(text).getTime();
    }

    public static String formatDate(String strDate, String originalFormat, String desiredFormat) {
        return convertToString(convertToCalendar(strDate, originalFormat), desiredFormat);
    }

    public static String getDayName(String date, String dateFormat) {
        Calendar calendar = convertToCalendar(date, dateFormat);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayName = sdf.format(calendar.getTime());

        return dayName;
    }

    public static String getDayName(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayName = sdf.format(date.getTime());

        return dayName;
    }

    public static long getCurrentTime() {

        long now = System.currentTimeMillis(); // 22:54:15

        return now;
    }

    public static boolean isCurrentDate(Calendar calendar) {
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        return (calendar.get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH))
                && calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR);
    }

    public static boolean isPastDate(Calendar calendar) {
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        return calendar.getTimeInMillis() < currentCalendar.getTimeInMillis();
    }

    public static boolean isToday(Calendar calendar) {
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        return currentCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String addDays(String strDate, String strFormat, int days) {
        Calendar calendar = convertToCalendar(strDate, strFormat);
        if (calendar != null) {
            calendar.add(Calendar.DATE, days);
            return convertToString(calendar, strFormat);
        } else {
            return null;
        }
    }
}
