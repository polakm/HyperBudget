package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;

class RangeData {

    private final int year;
    private final int month;
    private final String monthName;

    @JsonCreator
    private RangeData(int year, int month, String monthName) {
        this.year = year;
        this.month = month;
        this.monthName = monthName;
    }

    static RangeData of(int year, int month, String monthName) {
        return new RangeData(year, month, monthName);
    }

    int getYear() {
        return year;
    }

    int getMonth() {
        return month;
    }

    String getMonthName() {
        return monthName;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
