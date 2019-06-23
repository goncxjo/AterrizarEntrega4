package com.aterrizar.util.date.pattern;

public class ISO8601 extends DatePattern {

    public ISO8601() {
        this.DATE_PATTERN = "yyyy-MM-dd";
    }

    @Override
    String buildDateFormat(String textDate) {
        return textDate.substring(0,4) + "-" + textDate.substring(4,6) + "-" + textDate.substring(6,8);
    }
}
