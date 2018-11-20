package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.michalpolak.hyperbudget.transaction.core.api.AggregatedTransaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionAggregationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAggregationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionAggregationRestController.class);

    private TransactionAggregationService service;

    @Autowired
    public TransactionAggregationRestController( TransactionAggregationService service) {
        this.service = service;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public AggregatedTransactionData getTransaction(@PathVariable String id){

        AggregatedTransaction result = service.getAggregatedData(id);
        return new AggregatedTransactionData(result);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AggregatedTransactionData> transactionList(){

        List<AggregatedTransactionData> result = new ArrayList<>();
        List<AggregatedTransaction> transactions = service.getAggregatedTransactionsList();

        transactions.forEach(t-> result.add(new AggregatedTransactionData(t)));

        return result;
    }


}
