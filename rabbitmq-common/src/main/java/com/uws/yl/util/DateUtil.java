package com.uws.yl.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date  getNextTiem(Date date,int timeOut){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, timeOut);
        return calendar.getTime();
    }
}
