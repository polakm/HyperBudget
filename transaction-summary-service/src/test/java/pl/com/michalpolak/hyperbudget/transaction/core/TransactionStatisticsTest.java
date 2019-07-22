package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionStatisticsTest {


    public TransactionStatistics getTransactionStatistics(int ... amounts){

        List<TransactionInfo> transactions = new ArrayList<>();
        for (long amount : amounts) {
           BigDecimal decimalAmount = new BigDecimal(amount);

            TransactionInfo.Builder builder = TransactionInfo.builder();
            Transaction transaction = Transaction.builder().
                    withAmount(Money.of(CurrencyUnit.USD,amount))
                    .build();

            TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();
            transactions.add(transactionInfo);
        }
       return TransactionStatistics.of(transactions);
    }


    public TransactionStatistics getTransactionStatisticsForEmptyList(){

        List<TransactionInfo> transactions = new ArrayList<>();
        return TransactionStatistics.of(transactions);
    }



    @Test
    public void sumOfIncomes(){

        int[] amounts = {1000,-1000,-200,400,600};
        TransactionStatistics statistics = getTransactionStatistics(amounts);
        BigDecimal result = statistics.sumOfIncomes();
        assertEquals(new BigDecimal("2000.00"), result);
    }


    @Test
    public void sumOfIncomesForEmptyList(){

        TransactionStatistics statistics =   getTransactionStatisticsForEmptyList();
        BigDecimal result = statistics.sumOfIncomes();
        assertEquals(new BigDecimal("0"), result);
    }

    @Test
    public void sumOfExpenses(){
        int[] amounts = {1000,-1000,-200,400,600};
        TransactionStatistics statistics = getTransactionStatistics(amounts);
        BigDecimal result = statistics.sumOfExpenses();
        assertEquals(new BigDecimal("-1200.00"), result);
    }


    @Test
    public void sumOfExpensesForEmptyList(){
        TransactionStatistics statistics =   getTransactionStatisticsForEmptyList();
        BigDecimal result = statistics.sumOfExpenses();
        assertEquals(new BigDecimal("0"), result);
    }

    @Test
    public void totalSum(){
        int[] amounts = {1000,-1000,-200,400,600};
        TransactionStatistics statistics = getTransactionStatistics(amounts);
        BigDecimal result = statistics.totalSum();
        assertEquals(new BigDecimal("800.00"), result);
    }


    @Test
    public void totalSumForEmptyList(){
        TransactionStatistics statistics =   getTransactionStatisticsForEmptyList();
        BigDecimal result = statistics.totalSum();
        assertEquals(new BigDecimal("0"), result);
    }




}