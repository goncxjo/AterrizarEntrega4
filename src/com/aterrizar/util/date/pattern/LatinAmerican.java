package com.aterrizar.util.date.pattern;

public class LatinAmerican extends DatePattern {

    public LatinAmerican() {
        this.DATE_PATTERN = "dd/MM/yyyy";
    }

    @Override
    String buildDateFormat(String textDate) {
        return textDate.substring(0,2) + "/" + textDate.substring(2,4) + "/" + textDate.substring(4,8);
    }
}
