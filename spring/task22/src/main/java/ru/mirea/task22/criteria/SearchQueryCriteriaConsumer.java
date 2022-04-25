package ru.mirea.task22.criteria;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@Getter
@Setter
public class SearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root root;

    public SearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root root) {
        this.predicate = predicate;
        this.builder = builder;
        this.root = root;
    }

    @Override
    public void accept(SearchCriteria param) {
        String operation = param.getOperation();
        String key = param.getKey();
        Object value = param.getValue();

        try {
            if (operation.equalsIgnoreCase(">")) {
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(
                        root.get(key), value.toString()
                ));
            } else if (operation.equalsIgnoreCase("<")) {
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(
                        root.get(key), value.toString()
                ));
            } else if (operation.equalsIgnoreCase(":")) {
                if (root.get(key).getJavaType() == String.class) {
                    predicate = builder.and(predicate, builder.like(
                            root.get(key), "%" + value + "%"
                    ));
                } else {
                    predicate = builder.and(predicate, builder.equal(
                            root.get(key), value
                    ));
                }
            }
        } catch (IllegalArgumentException ex) {

        }
    }
}
