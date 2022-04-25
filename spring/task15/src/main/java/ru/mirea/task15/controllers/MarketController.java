package ru.mirea.task15.controllers;

import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task15.models.Market;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/markets")
public class MarketController extends BaseController<Market> {

    @GetMapping
    public List<Market> getAll() {
        return session.createQuery("from Market").getResultList();
    }

    @GetMapping("{id}")
    public Market getOneById(@PathVariable("id") Long id) {
        Query query = session.createQuery("from Market where id = " + id);
        Optional<Market> object = query.getResultStream().findFirst();

        if (object.isPresent()) {
            return object.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market doesn't found");
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
}
