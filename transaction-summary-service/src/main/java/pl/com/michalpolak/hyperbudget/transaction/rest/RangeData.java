package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.time.YearMonth;

public class RangeData {

    private int year;
    private int month;
    private String monthName;


    public RangeData(YearMonth yearMonth) {

        this.year = yearMonth.getYear();
        this.month = yearMonth.getMonthOfYear();
        this.monthName = yearMonth.monthOfYear().getAsText();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
