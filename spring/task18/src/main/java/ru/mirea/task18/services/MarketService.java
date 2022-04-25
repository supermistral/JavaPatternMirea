package ru.mirea.task18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task18.criteria.SearchCriteria;
import ru.mirea.task18.models.Market;
import ru.mirea.task18.models.dto.MarketDTO;
import ru.mirea.task18.models.dto.ProductDTO;
import ru.mirea.task18.repositories.MarketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketService implements IMarketService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MarketRepository repository;

    @Override
    public List<MarketDTO> findAll(List<SearchCriteria> params) {
        CriteriaQuery<Market> queryCriteria = SearchCriteria.getSearchQueryCriteria(
                params, entityManager, Market.class
        );

        return entityManager.createQuery(queryCriteria)
                .getResultStream()
                .map(m -> new MarketDTO(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public Market findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found"));
    }

    @Override
    public Market create(Market object) {
        return repository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllProductsById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found"))
                .getProducts()
                .stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
    }
}
