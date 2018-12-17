package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.TransactionStatistics;

public class StatisticsData {

    private String totalSum;
    private String sumOfIncomes;
    private String sumOfExpenses;


    public StatisticsData(TransactionStatistics statistics) {

        this.totalSum = statistics.totalSum().toString();
        this.sumOfIncomes = statistics.sumOfIncomes().toString();
        this.sumOfExpenses = statistics.sumOfExpenses().toString();
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }

    public String getSumOfIncomes() {
        return sumOfIncomes;
    }

    public void setSumOfIncomes(String sumOfIncomes) {
        this.sumOfIncomes = sumOfIncomes;
    }

    public String getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(String sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }
}
