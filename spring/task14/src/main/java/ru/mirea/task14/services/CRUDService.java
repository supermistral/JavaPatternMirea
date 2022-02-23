package ru.mirea.task14.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.task14.models.IndexedModel;

import java.util.List;
import java.util.Optional;

public abstract class CRUDService<E extends IndexedModel> {
    protected List<E> itemList;

    public List<E> getAll() {
        return itemList;
    }

    public E getOne(int id) {
        Optional<E> findProduct = itemList.stream()
                .filter(product -> product.getId() == id)
                .findFirst();

        if (findProduct.isPresent()) {
            return findProduct.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public void createOne(E product) {
        itemList.add(product);
    }

    public void deleteOne(int id) {
        itemList.removeIf(product -> product.getId() == id);
    }
}
