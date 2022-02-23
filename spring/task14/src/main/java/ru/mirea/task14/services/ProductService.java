package ru.mirea.task14.services;

import org.springframework.stereotype.Service;
import ru.mirea.task14.models.Product;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductService extends CRUDService<Product> {

    public ProductService() {
        super();

        itemList = new ArrayList<>(Arrays.asList(
                new Product("Item-1", 100)
        ));
    }
}
