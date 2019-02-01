package pl.com.michalpolak.hyperbudget.transaction.data.spi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.List;


public interface TransactionService {

    Transaction addTranasaction(@RequestBody Transaction transaction);

    List<Transaction> transactionList();

    Transaction getTranaction(@PathVariable("id") String id);

    void removeTranaction(@PathVariable("id") String id);

    void updateTranaction(@PathVariable("id") String id, @RequestBody Transaction transaction);
}
