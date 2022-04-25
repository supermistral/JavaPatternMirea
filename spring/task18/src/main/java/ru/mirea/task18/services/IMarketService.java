package ru.mirea.task18.services;

import ru.mirea.task18.models.Market;
import ru.mirea.task18.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
