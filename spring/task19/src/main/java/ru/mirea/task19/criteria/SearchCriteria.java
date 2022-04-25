package ru.mirea.task19.criteria;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public static <E> CriteriaQuery<E> getSearchQueryCriteria(
            List<SearchCriteria> params, EntityManager entityManager, Class<E> eClass
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> queryCriteria = builder.createQuery(eClass);
        Root root = queryCriteria.from(eClass);

        Predicate predicate = builder.conjunction();

        SearchQueryCriteriaConsumer searchQueryCriteriaConsumer = new SearchQueryCriteriaConsumer(
                predicate, builder, root
        );

        params.forEach(searchQueryCriteriaConsumer);

        predicate = searchQueryCriteriaConsumer.getPredicate();
        queryCriteria.where(predicate);

        return queryCriteria;
    }

    public String toString() {
        return key + operation + value.toString();
    }
}
