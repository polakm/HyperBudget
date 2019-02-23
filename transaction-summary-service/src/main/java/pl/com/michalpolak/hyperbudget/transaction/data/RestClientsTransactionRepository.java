package pl.com.michalpolak.hyperbudget.transaction.data;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.AccountService;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.CategoryService;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

class RestClientsTransactionRepository implements TransactionRepository {

    private TransactionService transactionService;
    private CategoryService categoryService;
    private AccountService accountService;

    RestClientsTransactionRepository(TransactionService transactionService, CategoryService categoryService, AccountService accountService) {

        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.accountService = accountService;
    }
    @Override
    public List<TransactionInfo> getTransactionInfosByMonth(YearMonth month) {

        List<TransactionInfo> transactionInfos = new ArrayList<TransactionInfo>();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        List<Transaction> transactions = transactionService.transactionList();
        transactions.stream().filter(t -> t.getExecutionDate().getMonthOfYear() == month.getMonthOfYear() && t.getExecutionDate().getYear() == month.getYear()).forEach(t -> {

            ListenableFuture<Category> categoryFuture = service.submit(() -> categoryService.getCategory(t.getCategoryId()));
            ListenableFuture<Account> accountFuture = service.submit(() -> accountService.getAccount(t.getAccountId()));
            TransactionInfo transactionInfo = collectData(t, categoryFuture, accountFuture);
            transactionInfos.add(transactionInfo);
        });
        return transactionInfos;
    }

    private TransactionInfo collectData(Transaction t, ListenableFuture<Category> categoryFuture, ListenableFuture<Account> accountFuture) {

        Category category = readCategoryFromFuture(categoryFuture);
        Account account = readAccountFromFuture(accountFuture);
        return new TransactionInfo(t, category, account);
    }

    private Account readAccountFromFuture(ListenableFuture<Account> accountFuture) {

        try {
            return accountFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Problem during account loading.",e);
        }

    }

    private Category readCategoryFromFuture(ListenableFuture<Category> categoryFuture) {

        try {
            return categoryFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Problem during category loading.",e);
        }
    }
}