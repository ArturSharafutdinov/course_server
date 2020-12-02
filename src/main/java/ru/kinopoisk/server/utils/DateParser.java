package ru.kinopoisk.server.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static DateFormatSymbols igromaniaDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", igromaniaDateFormatSymbols );

    public static Date parseRussianDate(String date) throws ParseException {

        return dateFormat.parse(date);
    }

}
