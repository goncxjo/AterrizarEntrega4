package com.aterrizar.util.date.pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DatePattern {
    protected String DATE_PATTERN;

    /**
     * Agrega los separadores a @textDate según DATE_PATTERN.
     * @param textDate recibe una fecha sin separadores
     * @return fecha con separadores según DATE_PATTERN
     * */
    abstract String buildDateFormat(String textDate);

    /**
     * Formatea el texto para que sea compatible con el formato del patrón
     * @param textDate texto que representa una fecha.
     * @return fecha formateada según DATE_PATTERN.
     * */
    String format(String textDate) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(textDate);

        String textDateWithoutSeparators = matcher.replaceAll("");
        return buildDateFormat(textDateWithoutSeparators);
    }

    /**
     * Parsea una texto que representa una fecha a un Date. El texto debe ser compatible con el DATE_PATTERN.
     * @param textDate texto que representa una fecha
     * @return objeto Date. Es la fecha representada por @textDate
     * */
    public Date parse(String textDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        textDate = format(textDate);
        Date date = null;
        try {
            date = format.parse(textDate);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return date;
    }
}
