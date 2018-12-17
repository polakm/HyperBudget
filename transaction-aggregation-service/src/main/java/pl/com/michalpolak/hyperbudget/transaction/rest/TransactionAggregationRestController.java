package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;
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


    @RequestMapping(path="/summary", method = RequestMethod.GET)
    public TransactionSummaryData transactionsSummary(){

        List<AggregatedTransactionData> result = new ArrayList<>();
        TransactionSummary summary = service.getTransactionsSummary();

        return  new TransactionSummaryData(summary);
    }


}
