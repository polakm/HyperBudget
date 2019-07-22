package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionRepository;

import java.util.List;


class BasicTransactionSummaryService implements TransactionSummaryService {

    private final TransactionRepository transactionRepository;

    BasicTransactionSummaryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionSummary getTransactionsSummaryPeMonth(YearMonth month) {
        List<TransactionInfo> transactionInfos = transactionRepository.getTransactionInfosByMonth(month);
        TransactionStatistics statistics = TransactionStatistics.of(transactionInfos);
        return TransactionSummary.of(transactionInfos, statistics);
    }

}
