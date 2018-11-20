package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.AggregatedTransaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionAggregationService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;

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
}
