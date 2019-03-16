package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.time.YearMonth;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class TransactionSummaryDataMapper {

    public TransactionSummaryData mapToData(TransactionSummary summary, YearMonth yearMonth) {

        TransactionSummaryData transactionSummaryData = new TransactionSummaryData();
        List<TransactionInfoData> transactions = new ArrayList<>();
        summary.getTransactionInfos().stream().map(t -> mapToTransactionInfoData(t)).collect(Collectors.toList());
        StatisticsData statistics = mapStatisticsData(summary.getStatistics());
        RangeData range = mapToRangeData(yearMonth);
        transactionSummaryData.setTransactions(transactions);
        transactionSummaryData.setStatistics(statistics);
        transactionSummaryData.setRange(range);
        return transactionSummaryData;

    }

    public TransactionInfoData mapToTransactionInfoData(TransactionInfo transactionInfo) {

        TransactionInfoData transactionInfoData = new TransactionInfoData();
        transactionInfoData.setId(transactionInfo.getId());
        transactionInfoData.setTitle(transactionInfo.getTitle());
        if (transactionInfo.getAccount() != null) {
            transactionInfoData.setAccountId(transactionInfo.getAccount().getId());
            transactionInfoData.setAccountName(transactionInfo.getAccount().getName());
        }
        if (transactionInfo.getCategory() != null) {
            transactionInfoData.setCategoryId(transactionInfo.getCategory().getId());
            transactionInfoData.setCategoryName(transactionInfo.getCategory().getName());
        }

        if (transactionInfo.getAmount() != null) {
            transactionInfoData.setAmount(transactionInfo.getAmount().getAmount().toPlainString());
            transactionInfoData.setCurrencyCode(transactionInfo.getAmount().getCurrencyUnit().getCode());
        }

        if (transactionInfo.getAmount() != null && transactionInfo.getAmount().isPositive()) {
            transactionInfoData.setType(TransactionTypes.INCOME);
        }

        if (transactionInfo.getAmount() != null && transactionInfo.getAmount().isNegative()) {
            transactionInfoData.setType(TransactionTypes.EXPENSE);
        }

        if (transactionInfo.getExecutionDate() != null) {
            transactionInfoData.setExecutionDate(transactionInfo.getExecutionDate().toString());
        }
        return transactionInfoData;
    }

    public StatisticsData mapStatisticsData(TransactionStatistics statistics) {

        StatisticsData statisticsData = new StatisticsData();
        statisticsData.setBalance(statistics.totalSum().toString());
        statisticsData.setIncome(statistics.sumOfIncomes().toString());
        statisticsData.setExpense(statistics.sumOfExpenses().toString());
        statisticsData.setBalanceAbs(statistics.totalSum().abs().toString());
        statisticsData.setIncomeAbs(statistics.sumOfIncomes().abs().toString());
        statisticsData.setExpenseAbs(statistics.sumOfExpenses().abs().toString());
        return statisticsData;
    }

    public RangeData mapToRangeData(YearMonth yearMonth) {

        RangeData rangeData = new RangeData();
        rangeData.setYear(yearMonth.getYear());
        rangeData.setMonth(yearMonth.getMonthOfYear());
        rangeData.setMonthName(yearMonth.monthOfYear().getAsText());
        return rangeData;
    }
}
