package ru.mirea.task17.controllers;

import org.hibernate.Session;
import ru.mirea.task17.criteria.SearchCriteria;
import ru.mirea.task17.criteria.SearchQueryCriteriaConsumer;
import ru.mirea.task17.models.IndexedModel;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseController<E extends IndexedModel> {
    @PersistenceContext
    private EntityManager entityManager;

    protected Session session = null;

    @PostConstruct
    void init() {
        session = entityManager.unwrap(Session.class);
    }

    public List<SearchCriteria> getSearchParams(String search) {
        List<SearchCriteria> params = new ArrayList<>();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }

        return params;
    }

    public CriteriaQuery<E> getSearchQueryCriteria(String search, Class<E> eClass) {
        List<SearchCriteria> params = getSearchParams(search);

        CriteriaBuilder builder = session.getCriteriaBuilder();
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

    public abstract List<?> getAll(String search);

    public abstract E getOneById(Long id);

    public abstract void createOne(E object);

    public abstract void deleteOne(Long id);
}
