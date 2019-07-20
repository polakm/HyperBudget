package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.YearMonth;
import org.joda.time.chrono.ISOChronology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;

import javax.ws.rs.HeaderParam;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/api/summaries")
class TransactionSummaryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionSummaryRestController.class);

    private final TransactionSummaryService service;
    private final TransactionSummaryDataMapper mapper;

    @Autowired
    TransactionSummaryRestController( TransactionSummaryService service, TransactionSummaryDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(path="/{year}/{month}", method = RequestMethod.GET, headers = {"X-API-Version=1"})
    TransactionSummaryData transactionsSummaryPerMonth(@PathVariable("year") int year , @PathVariable("month") int month){
        YearMonth yearMonth = new YearMonth(year,month);
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(yearMonth);
        TransactionSummaryData summaryData = mapper.mapToData(summary,yearMonth);
        Iterable<Link> links = this.buildLinks(year, month);
        summaryData.add(links);
        return summaryData;
    }



    @RequestMapping(path="/{year}/{month}", method = RequestMethod.GET, headers = {"X-API-Version=1.1"})
    TransactionSummaryData transactionsSummaryPerMonthWithTimeZone(@PathVariable("year") int year , @PathVariable("month") int month, @HeaderParam("X-Time-Zone") String timeZone){

        Chronology chronology = ISOChronology.getInstance(DateTimeZone.forID(timeZone));
        YearMonth yearMonth = new YearMonth(year,month,chronology);
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(yearMonth);
        TransactionSummaryData summaryData = mapper.mapToData(summary,yearMonth);
        Iterable<Link> links = this.buildLinks(year, month);
        summaryData.add(links);
        return summaryData;
    }


    private Collection<Link> buildLinks(int year, int month){

        Collection<Link> links = new ArrayList<>();
       Link selfLink = getSelfLink(year, month);
       links.add(selfLink);
       Link nextLink = getNextLink(year, month);
       links.add(nextLink);
       Link previousLink = getPreviousLink(year, month);
       links.add(previousLink);
       return links;
    }

    private Link getSelfLink( int year, int month) {
        return linkTo(methodOn(TransactionSummaryRestController.class).transactionsSummaryPerMonth(year,month)).withSelfRel();
    }

    private Link getNextLink(int year, int month) {
        YearMonth nextMonth = new YearMonth(year,month).plusMonths(1);
        return linkTo(methodOn(TransactionSummaryRestController.class).transactionsSummaryPerMonth(nextMonth.getYear(),nextMonth.getMonthOfYear())).withRel("nextMonth");
    }

    private Link getPreviousLink(int year, int month) {
        YearMonth previousMonth = new YearMonth(year,month).minusMonths(1);
        return linkTo(methodOn(TransactionSummaryRestController.class).transactionsSummaryPerMonth(previousMonth.getYear(),previousMonth.getMonthOfYear())).withRel("previousMonth");
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorData errorData =  ErrorData.of("f2f0f0","Unknown Error", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
