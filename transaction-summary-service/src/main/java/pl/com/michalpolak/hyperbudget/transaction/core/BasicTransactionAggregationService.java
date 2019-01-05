package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.*;

import java.util.ArrayList;
import java.util.List;


class BasicTransactionSummaryService implements TransactionSummaryService {

    private TransactionService transactionService;
    private CategoryService categoryService;
    private AccountService accountService;

    BasicTransactionSummaryService(TransactionService transactionService, CategoryService categoryService, AccountService accountService) {

        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.accountService = accountService;
    }

    @Override
    public TransactionInfo getTransactionInfo(String id){

        Transaction transaction = transactionService.getTranaction(id);
        Category category =categoryService.getCategory(transaction.getCategoryId());
        Account account = accountService.getAccount(transaction.getAccountId());
        return new TransactionInfo(transaction,category,account);
    }

    @Override
    public List<TransactionInfo> getTransactionInfosList() {

        List<TransactionInfo> transactionInfos = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();

        transactions.forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            transactionInfos.add(new TransactionInfo(t,category,account));
        });


        return transactionInfos;

    }

    @Override
    public TransactionSummary getTransactionsSummary() {

        List<TransactionInfo> transactionInfos = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();

        transactions.forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            transactionInfos.add(new TransactionInfo(t,category,account));
        });

        TransactionStatistics statistics = new TransactionStatistics(transactionInfos);
        return new TransactionSummary(transactionInfos, statistics);

    }

    @Override
    public TransactionStatistics calculateTransactionStatistics() {

        return new TransactionStatistics(getTransactionInfosList());
    }

    @Override
    public TransactionSummary getTransactionsSummaryPeMonth(YearMonth month) {
        List<TransactionInfo> transactionInfos = new ArrayList<>();

        List<Transaction> transactions =  transactionService.transactionList();
        transactions.stream().filter(t-> t.getExecutionDate().getMonthOfYear() == month.getMonthOfYear() && t.getExecutionDate().getYear() == month.getYear() ).forEach(t->{

            Category category =categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            transactionInfos.add(new TransactionInfo(t,category,account));
        });

        TransactionStatistics statistics = new TransactionStatistics(transactionInfos);
        return new TransactionSummary(transactionInfos, statistics);
    }


}
