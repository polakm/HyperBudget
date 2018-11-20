package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface TransactionService {

    Transaction addTranasaction(@RequestBody Transaction transactionData);

    List<Transaction> transactionList();

    Transaction getTranaction(@PathVariable("id") String id);

    void removeTranaction(@PathVariable("id") String id);

    void updateTranaction(@PathVariable("id") String id, @RequestBody Transaction transactionData);
}
