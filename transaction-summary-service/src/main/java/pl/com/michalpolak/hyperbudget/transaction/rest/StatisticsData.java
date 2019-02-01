package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;


class StatisticsData {

    private String balance;
    private String income;
    private String expense;
    private String balanceAbs;
    private String incomeAbs;
    private String expenseAbs;

    StatisticsData(TransactionStatistics statistics) {

        this.balance = statistics.totalSum().toString();
        this.income = statistics.sumOfIncomes().toString();
        this.expense = statistics.sumOfExpenses().toString();

        this.balanceAbs = statistics.totalSum().abs().toString();
        this.incomeAbs = statistics.sumOfIncomes().abs().toString();
        this.expenseAbs = statistics.sumOfExpenses().abs().toString();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getBalanceAbs() {
        return balanceAbs;
    }

    public void setBalanceAbs(String balanceAbs) {
        this.balanceAbs = balanceAbs;
    }

    public String getIncomeAbs() {
        return incomeAbs;
    }

    public void setIncomeAbs(String incomeAbs) {
        this.incomeAbs = incomeAbs;
    }

    public String getExpenseAbs() {
        return expenseAbs;
    }

    public void setExpenseAbs(String expenseAbs) {
        this.expenseAbs = expenseAbs;
    }
}
