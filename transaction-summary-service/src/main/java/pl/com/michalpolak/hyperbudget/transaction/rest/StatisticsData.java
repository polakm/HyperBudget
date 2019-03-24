package pl.com.michalpolak.hyperbudget.transaction.rest;

class StatisticsData {

    private String balance;
    private String income;
    private String expense;
    private String balanceAbs;
    private String incomeAbs;
    private String expenseAbs;

    public StatisticsData() {

    }

    public StatisticsData(String balance, String income, String expense, String balanceAbs, String incomeAbs, String expenseAbs) {
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.balanceAbs = balanceAbs;
        this.incomeAbs = incomeAbs;
        this.expenseAbs = expenseAbs;
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
