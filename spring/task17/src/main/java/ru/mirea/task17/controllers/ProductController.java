package ru.mirea.task17.controllers;

import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task17.models.Product;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController extends BaseController<Product> {

    @GetMapping
    public List<Product> getAll(
            @RequestParam(value = "search", required = false) String search
    ) {
        CriteriaQuery<Product> queryCriteria = getSearchQueryCriteria(search, Product.class);

        return session.createQuery(queryCriteria).getResultList();
    }

    @GetMapping("{id}")
    public Product getOneById(@PathVariable("id") Long id) {
        Query query = session.createQuery("from Product where id = " + id);
        Optional<Product> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            return object.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market doesn't found");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOne(@RequestBody Product object) {
        if (object != null) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        Query query = session.createQuery("from Product where id = " + id);
        Optional<Product> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            Product product = object.get();

            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
        }
    }
}
