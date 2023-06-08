package com.oracle.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static String pattern = "yyyy年MM月dd日";
    public static String parseString(Calendar calendar){
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static String parseString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
