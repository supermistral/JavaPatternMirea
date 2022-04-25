package ru.mirea.task20.services;

import ru.mirea.task20.models.Market;
import ru.mirea.task20.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
