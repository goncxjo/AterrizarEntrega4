package com.aterrizar.model.util.date;

import com.aterrizar.model.util.date.pattern.DatePattern;
import com.aterrizar.model.util.date.pattern.ISO8601;
import com.aterrizar.model.util.date.pattern.LatinAmerican;
import com.aterrizar.model.util.date.pattern.NorthAmerican;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static final String ISO8601_DATE_REGEXP = "^(?:[1-9][0-9]{3})\\-?(?:(?:0[13578]|1[02])\\-?31|(?:0[469]|11)\\-?30|02\\-?29|(?:0[0-9]|1[012])\\-?(?:0[1-9]|[12][0-9]))$";
    private static final String LATIN_AMERICAN_DATE_REGEXP = "^(?:31\\/?(?:0?[13578]|1[02])|30\\/?(?:0[13-9]|1[12])|29\\/?02|(?:0[1-9]|[12][0-9])\\/?(?:0[1-9]|1[012]))\\/?[1-9][0-9]{3}$";
    private static final String NORTH_AMERICAN_DATE_REGEXP = "^(?:(?:0[13578]|1[02])\\-?31|(?:0[13-9]|1[12])\\-?30|02\\-?29|(?:0[1-9]|1[012])\\-?(?:0[1-9]|[12][0-9]))\\-?[1-9][0-9]{3}$";

    private static final long DAY_IN_MILLISECONDS = 86400000L;

    /**
     * Parsea un texto que representa una fecha a un objeto Date con el primer patrón de fechas que coincida (flexible).
     * @param textDate texto que representa una fecha
     * @return objeto Date con la fecha indicada en @textDate
     * */
    public static Date parseToDate(String textDate) {
        DatePattern datePattern = matchDatePattern(textDate);
        return datePattern.parse(textDate);
    }

    /**
     * Parsea un texto que representa una fecha a un objeto Date según el formato de fecha internacional (ISO8601).
     * @param textDate texto que representa una fecha
     * @return objeto Date con la fecha indicada en @textDate
     * */
    public static Date parseFromISO8601(String textDate) {
        DatePattern datePattern = new ISO8601();
        return datePattern.parse(textDate);
    }

    /**
     * Parsea un texto que representa una fecha a un objeto Date según el formato de fecha Latinoamericano.
     * @param textDate texto que representa una fecha
     * @return objeto Date con la fecha indicada en @textDate
     * */
    public static Date parseFromLatinAmericanDate(String textDate) {
        DatePattern datePattern = new LatinAmerican();
        return datePattern.parse(textDate);
    }

    /**
     * Parsea un texto que representa una fecha a un objeto Date según el formato de fecha Norteamericano.
     * @param textDate texto que representa una fecha
     * @return objeto Date con la fecha indicada en @textDate
     * */
    public static Date parseFromNorthAmericanDate(String textDate) {
        DatePattern datePattern = new NorthAmerican();
        return datePattern.parse(textDate);
    }

    /**
     * Machea un texto que representa una fecha con los patrones de fecha disponibles hasta encontrar el que coincida.
     * @param textDate texto que representa una fecha
     * @return objeto DatePattern
     * */
    private static DatePattern matchDatePattern(String textDate) throws PatternDoesntMatchException {
        DatePattern datePattern = null;

        if (textDate.matches(ISO8601_DATE_REGEXP)) {
            datePattern = new ISO8601();
        } else if (textDate.matches(LATIN_AMERICAN_DATE_REGEXP)) {
            datePattern = new LatinAmerican();
        } else if (textDate.matches(NORTH_AMERICAN_DATE_REGEXP)) {
            datePattern = new NorthAmerican();
        } else {
            throw new PatternDoesntMatchException();
        }

        return datePattern;
    }

    /**
     * Calcula la diferencia entre dos fechas.
     * @param dateOne fecha inicial
     * @param dateTwo fecha final
     * @return número que representa la diferencia entre fechas. Sólo valores absolutos.
     * */
    public static int calculateDaysDifference(Date dateOne, Date dateTwo) {
        long differenceInMilliseconds = dateOne.getTime() - dateTwo.getTime();
        long daysDifference = differenceInMilliseconds / DAY_IN_MILLISECONDS;
        return Math.abs(Math.round(daysDifference));
    }

    /**
     * Determina si la primera fecha es anterior a la segunda.
     * @param dateOne fecha inicial
     * @param dateTwo fecha final
     * */
    public static boolean isBefore(Date dateOne, Date dateTwo) {
        return (dateOne.getTime() - dateTwo.getTime()) < 0;
    }

    public static String parseToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
