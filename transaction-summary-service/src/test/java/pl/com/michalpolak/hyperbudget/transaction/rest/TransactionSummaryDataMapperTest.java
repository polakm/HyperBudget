package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionType;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TransactionSummaryDataMapperTest {

    @Test
    public void mapToTransactionInfoDataWithPositiveAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();

        Transaction transaction = Transaction.builder().
                withAmount(Money.parse("USD 99999.99"))
                .build();

        TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transactionInfo);

        //then
        assertEquals(data.getAmount(), "99999.99");
        assertEquals(data.getCurrencyCode(), "USD");
        assertEquals(data.getType(), TransactionType.INCOME);
    }

    @Test
    public void mapToTransactionInfoDataWithNegativeAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        Transaction transaction = Transaction.builder().
                withAmount(Money.parse("USD -99999.99"))
                .build();

        TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();
        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transactionInfo);

        //then
        assertEquals(data.getAmount(), "-99999.99");
        assertEquals(data.getCurrencyCode(), "USD");
        assertEquals(data.getType(), TransactionType.EXPENSE);
    }

    @Test
    public void mapToTransactionInfoDataWithoutAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        Transaction transaction = Transaction.builder().
                withAmount(null)
                .build();
        TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();
        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transactionInfo);

        //then
        assertNull(data.getAmount());
        assertNull(data.getCurrencyCode());
        assertNull(data.getType());
    }

    @Test
    public void mapToTransactionInfoDataWithExecutionDateUTC() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        Transaction transaction = Transaction.builder().
                onExecutionDate(DateTime.parse("2019-03-18").withZoneRetainFields(DateTimeZone.UTC))
                .build();
        TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transactionInfo);

        //then
        assertEquals(data.getExecutionDate(), "2019-03-18T00:00:00.000Z");

    }

    @Test
    public void mapToTransactionInfoDataWithExecutionDateWithTimeZone() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        Transaction transaction = Transaction.builder().
                onExecutionDate(DateTime.parse("2019-03-18T18:00:00.000Z").withZoneRetainFields(DateTimeZone.forID("Asia/Tokyo")))
                .build();

        TransactionInfo transactionInfo = TransactionInfo.builder().withTransaction(transaction).build();
        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transactionInfo);

        //then
        assertEquals(data.getExecutionDate(), "2019-03-18T09:00:00.000Z");

    }

    @Test
    public void mapToTransactionInfoDataWithoutExecutionDate() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(builder.build());

        //then
        assertNull(data.getExecutionDate());

    }

    @Test
    public void mapToTransactionInfoDataWithAccount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.withAccount(Account.of("TEST-ID", "TEST-NAME"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(builder.build());

        //then
        assertEquals(data.getAccountId(), "TEST-ID");
        assertEquals(data.getAccountName(), "TEST-NAME");

    }

    @Test
    public void mapToTransactionInfoDataWithoutAccount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.withAccount(null);

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(builder.build());

        //then
        assertNull(data.getAccountId());
        assertNull(data.getAccountName());
    }

    @Test
    public void mapToTransactionInfoDataWithCategory() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.withCategory(Category.of("TEST-ID", "TEST-NAME"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(builder.build());

        //then
        assertEquals(data.getCategoryId(), "TEST-ID");
        assertEquals(data.getCategoryName(), "TEST-NAME");
    }

    @Test
    public void mapToTransactionInfoDataWithoutCategory() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.withCategory(null);

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(builder.build());

        //then
        assertNull(data.getCategoryId());
        assertNull(data.getCategoryName());
    }

}