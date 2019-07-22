package pl.com.michalpolak.hyperbudget.transaction.core.api;

import java.math.BigDecimal;
import java.util.List;

public class TransactionStatistics {

    private final BigDecimal sumOfIncomes;
    private final BigDecimal sumOfExpenses;
    private final BigDecimal totalSum;

    private TransactionStatistics(BigDecimal sumOfIncomes, BigDecimal sumOfExpenses, BigDecimal totalSum) {
        this.sumOfIncomes = sumOfIncomes;
        this.sumOfExpenses = sumOfExpenses;
        this.totalSum = totalSum;
    }

    public static TransactionStatistics of(List<TransactionInfo> transactions) {

        BigDecimal sumOfIncomes = calculateOfIncomes(transactions);
        BigDecimal sumOfExpenses = calculateOfExpenses(transactions);
        BigDecimal totalSum = calculateeTotalSum(transactions);
        return new TransactionStatistics(sumOfIncomes, sumOfExpenses, totalSum);
    }

    private static BigDecimal calculateOfIncomes(List<TransactionInfo> transactions) {
        return transactions.stream().filter(transaction -> transaction.getAmount() != null && transaction.getAmount().isPositive())
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private static BigDecimal calculateOfExpenses(List<TransactionInfo> transactions) {
        return transactions.stream().filter(transaction -> transaction.getAmount() != null && transaction.getAmount().isNegative())
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calculateeTotalSum(List<TransactionInfo> transactions) {
        return transactions.stream().filter(transaction -> transaction.getAmount() != null)
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal sumOfIncomes() {
        return sumOfIncomes;
    }

    public BigDecimal sumOfExpenses() {
        return sumOfExpenses;
    }

    public BigDecimal totalSum() {
        return totalSum;
    }
}
