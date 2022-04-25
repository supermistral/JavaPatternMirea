package ru.mirea.task22.services;

import ru.mirea.task22.models.Market;
import ru.mirea.task22.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
