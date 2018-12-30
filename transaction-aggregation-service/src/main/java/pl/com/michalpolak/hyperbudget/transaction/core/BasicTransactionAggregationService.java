package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.api.AggregatedTransaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionAggregationService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.*;

import java.util.ArrayList;
import java.util.List;


class BasicTransactionAggregationService implements TransactionAggregationService {

    private TransactionService transactionService;
    private CategoryService categoryService;
    private AccountService accountService;

    BasicTransactionAggregationService(TransactionService transactionService, CategoryService categoryService, AccountService accountService) {

        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.accountService = accountService;
    }

    @Override
    public AggregatedTransaction getAggregatedData(String id){

        Transaction transaction = transactionService.getTranaction(id);
        Category category =categoryService.getCategory(transaction.getCategoryId());
        Account account = accountService.getAccount(transaction.getAccountId());
        return new AggregatedTransaction(transaction,category,account);
    }

    @Override
    public List<AggregatedTransaction> getAggregatedTransactionsList() {

        List<AggregatedTransaction> aggregatedTransactions = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();

        transactions.forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            aggregatedTransactions.add(new AggregatedTransaction(t,category,account));
        });


        return aggregatedTransactions;

    }

    @Override
    public TransactionSummary getTransactionsSummary() {

        List<AggregatedTransaction> aggregatedTransactions = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();

        transactions.forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            aggregatedTransactions.add(new AggregatedTransaction(t,category,account));
        });

        TransactionStatistics statistics = new TransactionStatistics(transactions);
        return new TransactionSummary(aggregatedTransactions, statistics);

    }

    @Override
    public TransactionStatistics calculateTransactionStatistics() {
        List<Transaction> transactions =  transactionService.transactionList();
        
        return new TransactionStatistics(transactions);
    }

    @Override
    public TransactionSummary getTransactionsSummaryPeMonth(YearMonth month) {
        List<AggregatedTransaction> aggregatedTransactions = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();
        transactions.stream().filter(t-> t.getExecutionDate().getMonthOfYear() == month.getMonthOfYear() && t.getExecutionDate().getYear() == month.getYear() ).forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            aggregatedTransactions.add(new AggregatedTransaction(t,category,account));
        });

        TransactionStatistics statistics = new TransactionStatistics(transactions);
        return new TransactionSummary(aggregatedTransactions, statistics);
    }


}
