package pl.com.michalpolak.hyperbudget.transaction.rest;

class RangeData {

    private int year;
    private int month;
    private String monthName;

    public RangeData() {

    }

    public RangeData(int year, int month, String monthName) {
        this.year = year;
        this.month = month;
        this.monthName = monthName;
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
