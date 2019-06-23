package com.aterrizar.util.date.pattern;

public class NorthAmerican extends DatePattern {

    public NorthAmerican() {
        this.DATE_PATTERN = "MM-dd-yyyy";
    }

    @Override
    String buildDateFormat(String textDate) {
        return textDate.substring(0,2) + "-" + textDate.substring(2,4) + "-" + textDate.substring(4,8);
    }
}
