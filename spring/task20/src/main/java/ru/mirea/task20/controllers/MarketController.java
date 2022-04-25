package ru.mirea.task20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task20.models.Market;
import ru.mirea.task20.models.dto.MarketDTO;
import ru.mirea.task20.models.dto.ProductDTO;
import ru.mirea.task20.services.MarketService;

import java.util.List;

@RestController
@RequestMapping("api/markets")
public class MarketController extends BaseController<Market> {

    @Autowired
    private MarketService service;

    @GetMapping
    public List<MarketDTO> getAll(
            @RequestParam(value = "search", required = false) String search
    ) {
        return service.findAll(getSearchParams(search));
    }

    @GetMapping("{id}")
    public Market getOneById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOne(@RequestBody Market object) {
        service.create(object);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @GetMapping("{id}/products")
    public List<ProductDTO> getAllProductsById(@PathVariable("id") Long id) {
        return service.findAllProductsById(id);
    }
}
