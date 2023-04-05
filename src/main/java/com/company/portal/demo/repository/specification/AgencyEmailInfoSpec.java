package com.company.portal.demo.repository.specification;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.repository.common.AbstractQuerySpecification;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AgencyEmailInfoSpec extends AbstractQuerySpecification<Survey> {
    private Long financialAdvisorRegistryNo;
    private String partyNo;
    private String emailTo;
    private String emailCC;

    @Override
    public Predicate toPredicate(Root<Survey> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        addFieldEqualsPredicate(cb, root, predicates, "financialAdvisorRegistryNo");
        addFieldEqualsPredicate(cb, root, predicates, "partyNo");
        addFieldNameLikeWithPredicateLower(cb, root, predicates, "emailTo");
        addFieldNameLikeWithPredicateLower(cb, root, predicates, "emailCC");
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}