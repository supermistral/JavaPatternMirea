package ru.mirea.task17.controllers;

import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task17.models.Market;
import ru.mirea.task17.models.dto.MarketDTO;
import ru.mirea.task17.models.dto.ProductDTO;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/markets")
public class MarketController extends BaseController<Market> {

    @GetMapping
    public List<MarketDTO> getAll(
            @RequestParam(value = "search", required = false) String search
    ) {
        CriteriaQuery<Market> queryCriteria = getSearchQueryCriteria(search, Market.class);

        return session.createQuery(queryCriteria)
                .getResultStream()
                .map(m -> new MarketDTO(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Market getOneById(@PathVariable("id") Long id) {
        Query query = session.createQuery("from Market where id = " + id);
        Optional<Market> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            return object.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOne(@RequestBody Market object) {
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
        Query query = session.createQuery("from Market where id = " + id);
        Optional<Market> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            Market market = object.get();

            Transaction transaction = session.beginTransaction();
            session.remove(market);
            transaction.commit();
        }
    }

    @GetMapping("{id}/products")
    public List<ProductDTO> getAllProductsById(@PathVariable("id") Long id) {
        Query query = session.createQuery("from Market where id = " + id, Market.class);
        Optional<Market> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            return object
                    .get()
                    .getProducts()
                    .stream()
                    .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
