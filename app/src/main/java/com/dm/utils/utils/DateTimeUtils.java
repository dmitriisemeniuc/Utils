package com.dm.utils.utils;

import java.util.Calendar;
import java.util.TimeZone;

public final class DateTimeUtils {

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static long getCurrentTimeGMT(){
        Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
        return mCalendar.getTimeInMillis();
    }
}
