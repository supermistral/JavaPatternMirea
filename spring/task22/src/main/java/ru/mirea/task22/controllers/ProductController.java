package ru.mirea.task22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task22.models.Product;
import ru.mirea.task22.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController extends BaseController<Product> {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll(
            @RequestParam(value = "search", required = false) String search
    ) {
        return service.findAll(getSearchParams(search));
    }

    @GetMapping("{id}")
    public Product getOneById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOne(@RequestBody Product object) {
        service.create(object);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
