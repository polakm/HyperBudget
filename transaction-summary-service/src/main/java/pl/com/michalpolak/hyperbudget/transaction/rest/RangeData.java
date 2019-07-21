package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;

class RangeData {

    private int year;
    private int month;
    private String monthName;

     RangeData() {

    }

     RangeData(int year, int month, String monthName) {
        this.year = year;
        this.month = month;
        this.monthName = monthName;
    }

     int getYear() {
        return year;
    }

     void setYear(int year) {
        this.year = year;
    }

     int getMonth() {
        return month;
    }

     void setMonth(int month) {
        this.month = month;
    }

     String getMonthName() {
        return monthName;
    }

     void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
