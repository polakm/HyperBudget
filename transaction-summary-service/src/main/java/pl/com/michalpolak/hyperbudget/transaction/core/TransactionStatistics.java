package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;

import java.math.BigDecimal;
import java.util.List;

public class TransactionStatistics {

     private List<TransactionInfo> transactions;

    public TransactionStatistics(List<TransactionInfo> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal totalSum(){

        BigDecimal totalSum = transactions.stream().filter(transaction -> transaction.getAmount() != null)
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
            return totalSum;
    }

    public BigDecimal sumOfIncomes(){
        BigDecimal sumOfIncomes = transactions.stream().filter(transaction -> transaction.getAmount()!= null && transaction.getAmount().isPositive() )
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumOfIncomes;
    }

    public BigDecimal sumOfExpenses(){
        BigDecimal sumOfExpenses = transactions.stream().filter(transaction -> transaction.getAmount()!= null && transaction.getAmount().isNegative() )
                .map(transaction -> transaction.getAmount().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return  sumOfExpenses;
    }


}
