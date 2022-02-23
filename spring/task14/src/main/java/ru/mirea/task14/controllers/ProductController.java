package ru.mirea.task14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task14.models.Product;
import ru.mirea.task14.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProductList() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.getOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody Product product) {
        productService.createOne(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteOne(id);
    }
}
