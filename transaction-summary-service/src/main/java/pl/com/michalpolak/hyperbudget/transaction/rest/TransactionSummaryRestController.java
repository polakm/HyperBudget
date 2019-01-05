package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummaryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/transactions")
public class TransactionSummaryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionSummaryRestController.class);

    private TransactionSummaryService service;

    @Autowired
    public TransactionSummaryRestController( TransactionSummaryService service) {
        this.service = service;
    }


    @RequestMapping(path="/summary", method = RequestMethod.GET)
    public Resource<TransactionSummaryData> transactionsSummary(){

        List<TransactionInfoData> result = new ArrayList<>();
        TransactionSummary summary = service.getTransactionsSummary();
        TransactionSummaryData summaryData = new TransactionSummaryData(summary);
        Link link = linkTo(methodOn(TransactionSummaryRestController.class).transactionsSummary()).withSelfRel();
        Resource<TransactionSummaryData> resource = new Resource<>(summaryData, link);
        return resource;
    }



    @RequestMapping(path="/summary/{year}/{month}", method = RequestMethod.GET)
    public Resource<TransactionSummaryData> transactionsSummaryPerMonth(@PathVariable("year") int year , @PathVariable("month") int month){

        List<TransactionInfoData> result = new ArrayList<>();
        TransactionSummary summary = service.getTransactionsSummaryPeMonth(new YearMonth(year,month));
        TransactionSummaryData summaryData = new TransactionSummaryData(summary);
        Iterable<Link> links = this.buildLinks(year, month);
        Resource<TransactionSummaryData> resource = new Resource<>(summaryData,links);
        return resource;
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


}
