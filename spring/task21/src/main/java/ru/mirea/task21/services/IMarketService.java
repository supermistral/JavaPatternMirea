package ru.mirea.task21.services;

import ru.mirea.task21.models.Market;
import ru.mirea.task21.models.dto.ProductDTO;

import java.util.List;

public interface IMarketService extends IBaseService<Market> {
    List<ProductDTO> findAllProductsById(Long id);
}
