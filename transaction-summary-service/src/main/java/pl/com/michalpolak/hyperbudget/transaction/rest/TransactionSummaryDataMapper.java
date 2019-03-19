package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.YearMonth;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        Optional.ofNullable(transactionInfo.getAccount()).filter(account -> !account.getId().isEmpty()).ifPresent(account->{
            transactionInfoData.setAccountId(account.getId());
            transactionInfoData.setAccountName(account.getName());
        });

        Optional.ofNullable(transactionInfo.getCategory()).filter(category -> !category.getId().isEmpty()).ifPresent(category->{
            transactionInfoData.setCategoryId(category.getId());
            transactionInfoData.setCategoryName(category.getName());
        });

        Optional.ofNullable(transactionInfo.getAmount()).ifPresent(ammount->{
            transactionInfoData.setAmount(ammount.getAmount().toPlainString());
            transactionInfoData.setCurrencyCode(ammount.getCurrencyUnit().getCode());
        });

        Optional.ofNullable(transactionInfo.getAmount()).filter(Money::isPositive).ifPresent(ammount->{
            transactionInfoData.setType(TransactionTypes.INCOME);
        });

        Optional.ofNullable(transactionInfo.getAmount()).filter(Money::isNegative).ifPresent(ammount->{
            transactionInfoData.setType(TransactionTypes.EXPENSE);
        });

        Optional.ofNullable(transactionInfo.getExecutionDate()).
                map(d->d.toDateTime(DateTimeZone.UTC)).
                map(DateTime::toString).
                ifPresent(transactionInfoData::setExecutionDate);

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
