package com.emagazine.api.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class StandardCalendarUtils {
    public static Boolean compareStandardMonth(Calendar compareCal, Calendar baseCal, int diffMonth) {
        Calendar modifyCal = Calendar.getInstance();
        modifyCal.setTime(compareCal.getTime());
        modifyCal.add(Calendar.MONTH, -diffMonth);

        return modifyCal.get(Calendar.YEAR) == baseCal.get(Calendar.YEAR)
                && modifyCal.get(Calendar.MONTH) == baseCal.get(Calendar.MONTH);
    }
}
