package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;

class StatisticsData {

    private final String balance;
    private final String income;
    private final String expense;
    private final String balanceAbs;
    private final String incomeAbs;
    private final String expenseAbs;

    private StatisticsData(String balance, String income, String expense, String balanceAbs, String incomeAbs, String expenseAbs) {
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.balanceAbs = balanceAbs;
        this.incomeAbs = incomeAbs;
        this.expenseAbs = expenseAbs;
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private String balance;
        private String income;
        private String expense;
        private String balanceAbs;
        private String incomeAbs;
        private String expenseAbs;

        Builder withBalance(String balance) {
            this.balance = balance;
            return this;
        }

        Builder withIncome(String income) {
            this.income = income;
            return this;
        }

        Builder withExpense(String expense) {
            this.expense = expense;
            return this;
        }

        Builder withBalanceAbs(String balanceAbs) {
            this.balanceAbs = balanceAbs;
            return this;
        }

        Builder withIncomeAbs(String incomeAbs) {
            this.incomeAbs = incomeAbs;
            return this;
        }

        Builder withExpenseAbs(String expenseAbs) {
            this.expenseAbs = expenseAbs;
            return this;
        }

        StatisticsData build() {
            return new StatisticsData(balance, income, expense, balanceAbs, incomeAbs, expenseAbs);
        }
    }

    String getBalance() {
        return balance;
    }

    String getIncome() {
        return income;
    }

    String getExpense() {
        return expense;
    }

    String getBalanceAbs() {
        return balanceAbs;
    }

    String getIncomeAbs() {
        return incomeAbs;
    }

    String getExpenseAbs() {
        return expenseAbs;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
