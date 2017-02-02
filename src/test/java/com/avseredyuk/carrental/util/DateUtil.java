package com.avseredyuk.carrental.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by lenfer on 1/28/17.
 */
public class DateUtil {
    public static boolean equalsDates(Date expected, Date value) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return f.format(expected).equals(f.format(value));
    }

    public static Date getDateAfter(Date dateFrom) {
        Random r = new Random();
        Calendar c = Calendar.getInstance();
        c.setTime(dateFrom);
        c.add(Calendar.DATE, r.nextInt(100));
        c.add(Calendar.HOUR, r.nextInt(100));
        c.add(Calendar.MINUTE, r.nextInt(100));
        c.add(Calendar.YEAR, r.nextInt(2));
        return new Date(c.getTime().getTime() / 1000 * 1000);
    }
}
