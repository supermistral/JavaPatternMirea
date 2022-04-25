package ru.mirea.task19.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task19.criteria.SearchCriteria;
import ru.mirea.task19.models.Market;
import ru.mirea.task19.models.Product;
import ru.mirea.task19.models.dto.MarketDTO;
import ru.mirea.task19.models.dto.ProductDTO;
import ru.mirea.task19.repositories.MarketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MarketService implements IMarketService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MarketRepository repository;

    @Override
    public List<MarketDTO> findAll(List<SearchCriteria> params) {
        log.info("Find all markets by search params: {}", params.toString());

        CriteriaQuery<Market> queryCriteria = SearchCriteria.getSearchQueryCriteria(
                params, entityManager, Market.class
        );

        return entityManager.createQuery(queryCriteria)
                .getResultStream()
                .map(this::convertToMarketDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Market findById(Long id) {
        log.info("Find market by id = {}", id);

        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found"));
    }

    @Override
    public Market create(Market object) {
        log.info("Saving market: {}", object);

        return repository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting market by id = {}", id);

        repository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllProductsById(Long id) {
        log.info("Finding market's products by id = {}", id);

        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market not found"))
                .getProducts()
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    private MarketDTO convertToMarketDTO(Market m) {
        return new MarketDTO(m.getId(), m.getName(), m.getAddress());
    }

    private ProductDTO convertToProductDTO(Product p) {
        return new ProductDTO(p.getId(), p.getName(), p.getPrice());
    }
}
