package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionTypes;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;

import static org.junit.Assert.*;


public class TransactionSummaryDataMapperTest {

    @Test
    public void mapToTransactonInfoDataWithPositiveAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setAmount(Money.parse("USD 99999.99"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getAmount(), "99999.99");
        assertEquals(data.getCurrencyCode(), "USD");
        assertEquals(data.getType(), TransactionTypes.INCOME);
    }

    @Test
    public void mapToTransactonInfoDataWithNegativeAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setAmount(Money.parse("USD -99999.99"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getAmount(), "-99999.99");
        assertEquals(data.getCurrencyCode(), "USD");
        assertEquals(data.getType(), TransactionTypes.EXPENSE);
    }

    @Test
    public void mapToTransactonInfoDataWithoutAmount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setAmount(null);

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertNull(data.getAmount());
        assertNull(data.getCurrencyCode());
        assertNull(data.getType());
    }

    @Test
    public void mapToTransactonInfoDataWithExecutionDateUTC() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setExecutionDate(DateTime.parse("2019-03-18").withZoneRetainFields(DateTimeZone.UTC));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getExecutionDate(), "2019-03-18T00:00:00.000Z");

    }

    @Test
    public void mapToTransactonInfoDataWithExecutionDateWithTimeZone() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setExecutionDate(DateTime.parse("2019-03-18T18:00:00.000Z").withZoneRetainFields(DateTimeZone.forID("Asia/Tokyo")));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getExecutionDate(), "2019-03-18T09:00:00.000Z");

    }

    @Test
    public void mapToTransactonInfoDataWithoutExecutionDate() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertNull(data.getExecutionDate());

    }

    @Test
    public void mapToTransactonInfoDataWithAccount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setAccount(new Account("TEST-ID", "TEST-NAME"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getAccountId(), "TEST-ID");
        assertEquals(data.getAccountName(), "TEST-NAME");

    }

    @Test
    public void mapToTransactonInfoDataWithoutAccount() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setAccount(null);

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertNull(data.getAccountId());
        assertNull(data.getAccountName());
    }

    @Test
    public void mapToTransactonInfoDataWithCategory() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setCategory(new Category("TEST-ID", "TEST-NAME"));

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertEquals(data.getCategoryId(), "TEST-ID");
        assertEquals(data.getCategoryName(), "TEST-NAME");
    }

    @Test
    public void mapToTransactonInfoDataWithoutCategory() {

        //given
        TransactionSummaryDataMapper mapper = new TransactionSummaryDataMapper();
        TransactionInfo transctionInfo = new TransactionInfo();
        transctionInfo.setCategory(null);

        //when
        TransactionInfoData data = mapper.mapToTransactionInfoData(transctionInfo);

        //then
        assertNull(data.getCategoryId());
        assertNull(data.getCategoryName());
    }

}