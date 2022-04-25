package ru.mirea.task18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task18.criteria.SearchCriteria;
import ru.mirea.task18.models.Product;
import ru.mirea.task18.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll(List<SearchCriteria> params) {
        CriteriaQuery<Product> queryCriteria = SearchCriteria.getSearchQueryCriteria(
                params, entityManager, Product.class
        );

        return entityManager.createQuery(queryCriteria).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Product create(Product object) {
        return repository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
