package com.konvergion.personalfinance.minitransactionsearchapi.helpers;

import com.konvergion.personalfinance.minitransactionsearchapi.model.GenericSpecification;
import com.konvergion.personalfinance.minitransactionsearchapi.model.SearchCriteria;
import com.konvergion.personalfinance.minitransactionsearchapi.model.SearchOperation;
import com.konvergion.personalfinance.minitransactionsearchapi.model.dto.TransactionFilterDto;
import com.konvergion.personalfinance.minitransactionsearchapi.model.entity.AccountTransactionEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class QuerySpecificationHelper {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public GenericSpecification<AccountTransactionEntity> generateSpecification(TransactionFilterDto filterDto, List<String> accountIds) {
        GenericSpecification<AccountTransactionEntity> specification = new GenericSpecification<>(new ArrayList<>());
        if (Objects.nonNull(filterDto.getDescription())) {
            specification.add(new SearchCriteria("transactionDescription",
                    filterDto.getDescription(), SearchOperation.MATCH));
        }

        if (Objects.nonNull(filterDto.getCategory())) {
            specification.add(new SearchCriteria("transactionCategory",
                    filterDto.getCategory(), SearchOperation.MATCH));
        }

        if (CollectionUtils.isNotEmpty(accountIds)) {
            specification.add(new SearchCriteria("accountId",
                    accountIds, SearchOperation.IN_CLAUSE));
        }

        if (Objects.nonNull(filterDto.getMonthSelector())) {
            String selectedMonth = filterDto.getMonthSelector().getSelectedMonth();
            String selectedYear = filterDto.getMonthSelector().getSelectedYear();

            if ((Objects.nonNull(selectedMonth) && StringUtils.isNotEmpty(selectedMonth))
                    && (Objects.nonNull(selectedYear) && StringUtils.isNotEmpty(selectedMonth))) {
                int year = Integer.parseInt(selectedYear);
                int month = Integer.parseInt(selectedMonth);
                if (year > 0 && month > 0) {
                    YearMonth yearMonth = YearMonth.of(year, month);
                    addDateConditions(specification, yearMonth.atDay(1), yearMonth.atEndOfMonth());
                }

            }
        }

        if (Objects.nonNull(filterDto.getDatePicker())) {
            String beginDate = filterDto.getDatePicker().getBeginDate();
            String endDate = filterDto.getDatePicker().getEndDate();

            if ((Objects.nonNull(beginDate) && StringUtils.isNotEmpty(beginDate))
                    && (Objects.nonNull(endDate) && StringUtils.isNotEmpty(endDate))) {
                addDateConditions(specification, LocalDate.parse(beginDate, FORMATTER), LocalDate.parse(endDate, FORMATTER));

            }
        }
        return specification;
    }

    private void addDateConditions(GenericSpecification<AccountTransactionEntity> specification,
                                   LocalDate beginDate, LocalDate endDate) {
        specification.add(new SearchCriteria("postedDate",
                beginDate, SearchOperation.DATE_GREATER_THAN_EQUAL));


        specification.add(new SearchCriteria("postedDate",
                endDate, SearchOperation.DATE_LESS_THAN_EQUAL));
    }
}
