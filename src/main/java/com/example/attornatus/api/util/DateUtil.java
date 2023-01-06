package com.example.attornatus.api.util;

import com.example.attornatus.api.exception.IncorrectDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    /**
     * @param date recive a LocalDate
     * @return a String with dd-MM-yyyy format
     */
    public static String stringFormat(LocalDate date) {

        return date.format(dateTimeFormatter);
    }

    public static LocalDate stringToDate(String data) {

        try {
          return LocalDate.parse(data,dateTimeFormatter);
        } catch (Exception e) {
            throw new IncorrectDateFormat("o formato da data deve ser dd-mm-yyyy");
        }
    }


}
