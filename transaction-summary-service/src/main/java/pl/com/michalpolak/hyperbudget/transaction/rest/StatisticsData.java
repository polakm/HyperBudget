package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;

class StatisticsData {

    private String balance;
    private String income;
    private String expense;
    private String balanceAbs;
    private String incomeAbs;
    private String expenseAbs;

     StatisticsData() {

    }

     StatisticsData(String balance, String income, String expense, String balanceAbs, String incomeAbs, String expenseAbs) {
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.balanceAbs = balanceAbs;
        this.incomeAbs = incomeAbs;
        this.expenseAbs = expenseAbs;
    }

     String getBalance() {
        return balance;
    }

     void setBalance(String balance) {
        this.balance = balance;
    }

     String getIncome() {
        return income;
    }

     void setIncome(String income) {
        this.income = income;
    }

     String getExpense() {
        return expense;
    }

     void setExpense(String expense) {
        this.expense = expense;
    }

     String getBalanceAbs() {
        return balanceAbs;
    }

     void setBalanceAbs(String balanceAbs) {
        this.balanceAbs = balanceAbs;
    }

     String getIncomeAbs() {
        return incomeAbs;
    }

     void setIncomeAbs(String incomeAbs) {
        this.incomeAbs = incomeAbs;
    }

     String getExpenseAbs() {
        return expenseAbs;
    }

     void setExpenseAbs(String expenseAbs) {
        this.expenseAbs = expenseAbs;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
