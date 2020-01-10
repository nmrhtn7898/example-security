package com.syj.jspexample.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateTimeToStr(LocalDateTime dateTime) {
        return df.format(dateTime);
    }
}
