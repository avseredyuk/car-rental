package com.avseredyuk.carrental.web.util;

import com.avseredyuk.carrental.domain.Automobile;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenfer on 1/19/17.
 */
public class InvoiceUtil {
    private InvoiceUtil() {
    }

    public static Integer calculateSum(Automobile automobile, Date dateFrom, Date dateTo) {
        long diffInMillies = dateTo.getTime() - dateFrom.getTime();
        int hours = (int) TimeUnit.HOURS.convert(diffInMillies,TimeUnit.MILLISECONDS);
        int days = hours / 24;
        days += (hours % 24 == 0) ? 0 : 1;
        return days * automobile.getPricePerDay();
    }
}
