package ru.mirea.task19.services;

import ru.mirea.task19.models.Market;
import ru.mirea.task19.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
