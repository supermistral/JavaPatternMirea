package ru.mirea.task19.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task19.criteria.SearchCriteria;
import ru.mirea.task19.models.Product;
import ru.mirea.task19.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll(List<SearchCriteria> params) {
        log.info("Find all products by search params: {}", params.toString());

        CriteriaQuery<Product> queryCriteria = SearchCriteria.getSearchQueryCriteria(
                params, entityManager, Product.class
        );

        return entityManager.createQuery(queryCriteria).getResultList();
    }

    @Override
    public Product findById(Long id) {
        log.info("Find product by id = {}", id);

        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Product create(Product object) {
        log.info("Saving product: {}", object);

        return repository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting product by id = {}", id);

        repository.deleteById(id);
    }
}
