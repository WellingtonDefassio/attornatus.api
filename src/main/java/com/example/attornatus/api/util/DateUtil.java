package com.example.attornatus.api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {


    /**
     * @param date recive a LocalDate
     * @return a String with dd-MM-yyyy format
     */
    public static String stringFormat(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(dateTimeFormatter);
    }


}
