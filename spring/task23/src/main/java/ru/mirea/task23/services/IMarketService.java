package ru.mirea.task23.services;

import ru.mirea.task23.models.Market;
import ru.mirea.task23.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
