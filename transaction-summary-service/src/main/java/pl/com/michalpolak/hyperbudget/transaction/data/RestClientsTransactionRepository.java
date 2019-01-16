package pl.com.michalpolak.hyperbudget.transaction.data;

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

        List<Transaction> transactions = transactionService.transactionList();
        transactions.stream().filter(t -> t.getExecutionDate().getMonthOfYear() == month.getMonthOfYear() && t.getExecutionDate().getYear() == month.getYear()).forEach(t -> {

            Category category = categoryService.getCategory(t.getCategoryId());
            Account account = accountService.getAccount(t.getAccountId());
            transactionInfos.add(new TransactionInfo(t, category, account));
        });
        return transactionInfos;
    }
}