package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.TransactionStatistics;

// TODO: Change access to package
public class StatisticsData {

    private String totalSum;
    private String sumOfIncomes;
    private String sumOfExpenses;
    private String absTotalSum;
    private String absSumOfIncomes;
    private String absSumOfExpenses;

    //FIXME: Refactor property names
    public StatisticsData(TransactionStatistics statistics) {

        this.totalSum = statistics.totalSum().toString();
        this.sumOfIncomes = statistics.sumOfIncomes().toString();
        this.sumOfExpenses = statistics.sumOfExpenses().toString();

        this.absTotalSum = statistics.totalSum().abs().toString();
        this.absSumOfIncomes = statistics.sumOfIncomes().abs().toString();
        this.absSumOfExpenses = statistics.sumOfExpenses().abs().toString();
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

    public String getAbsTotalSum() {
        return absTotalSum;
    }

    public void setAbsTotalSum(String absTotalSum) {
        this.absTotalSum = absTotalSum;
    }

    public String getAbsSumOfIncomes() {
        return absSumOfIncomes;
    }

    public void setAbsSumOfIncomes(String absSumOfIncomes) {
        this.absSumOfIncomes = absSumOfIncomes;
    }

    public String getAbsSumOfExpenses() {
        return absSumOfExpenses;
    }

    public void setAbsSumOfExpenses(String absSumOfExpenses) {
        this.absSumOfExpenses = absSumOfExpenses;
    }
}
