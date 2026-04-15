package com.konvergion.personalfinance.minitransactionsearchapi.model;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GenericSpecification<T> implements Specification<T> {
    private List<SearchCriteria> criteriaList;

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Specification<T> and(Specification<T> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<T> or(Specification<T> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case MATCH:
                    predicates.add(builder.like(
                            builder.upper(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase() + "%"));
                    break;
                case DATE_GREATER_THAN_EQUAL:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    break;
                case DATE_LESS_THAN_EQUAL:
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    break;
                case IN_CLAUSE:
                    CriteriaBuilder.In<String> inClause = builder.in(root.get(criteria.getKey()));
                    List<String> values = (List<String>) criteria.getValue();
                    for (String value1 : values) {
                        inClause.value(value1);
                    }

                    predicates.add(inClause);
                    break;

            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
