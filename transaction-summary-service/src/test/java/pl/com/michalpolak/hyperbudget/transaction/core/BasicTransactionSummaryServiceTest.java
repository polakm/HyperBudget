package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicTransactionSummaryServiceTest {


    @Test
    public void getTransactionsPerMonth(){

        //given
        String[] dates = {"2020-12-10","2018-12-20","2018-02-14"};
        List<Transaction> transactionList = getTransactionsForDates(dates);
        TransactionSummaryService service = getTransactionSummaryService(transactionList);

        //when
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(new YearMonth(2018,12));

        //then
        assertEquals(1, summary.getTransactionInfos().size());
        assertEquals(12,summary.getTransactionInfos().get(0).getExecutionDate().getMonthOfYear());
        assertEquals(2018,summary.getTransactionInfos().get(0).getExecutionDate().getYear());
    }

    @Test
    public void getTransactionsPerMonthZeroPositiveMatches(){

        //given
        String[] dates = {"2008-12-02","2018-10-14","2019-12-10","2018-11-20","2016-12-14","2018-02-11"};
        List<Transaction> transactionList = getTransactionsForDates(dates);
        TransactionSummaryService service = getTransactionSummaryService(transactionList);

        //when
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(new YearMonth(2018,12));

        //then
        assertEquals(0, summary.getTransactionInfos().size());
    }

    @Test
    public void getTransactionsPerMonthMorePositiveMatches(){

        //given
        String[] dates = {"2018-12-02","2018-10-14","2019-12-10","2018-12-20","2016-12-14","2018-12-11"};
        List<Transaction> transactionList = getTransactionsForDates(dates);
        TransactionSummaryService service = getTransactionSummaryService(transactionList);

        //when
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(new YearMonth(2018,12));

        //then
        assertEquals(3, summary.getTransactionInfos().size());
    }


    private TransactionSummaryService getTransactionSummaryService(List<Transaction> transactionList) {
        TransactionService transactionService = mock(TransactionService.class);
        when(transactionService.transactionList()).thenReturn(transactionList);
        CategoryService categoryService = mock(CategoryService.class);
        AccountService accountService = mock(AccountService.class);

        return TransactionSummaryConfiguration.transactionSummaryServiceBean(transactionService,categoryService, accountService);
    }

    private List<Transaction> getTransactionsForDates(String[] dates) {
        List<Transaction> transactionList = new ArrayList();

        for (String date : dates) {
            Transaction transaction = new Transaction();
            transaction.setExecutionDate(DateTime.parse(date));
            transactionList.add(transaction);
        }
        return transactionList;
    }

}