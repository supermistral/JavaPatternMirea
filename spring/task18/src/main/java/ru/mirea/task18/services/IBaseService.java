package ru.mirea.task18.services;

import ru.mirea.task18.criteria.SearchCriteria;

import java.util.List;

public interface IBaseService<E> {
    List<?> findAll(List<SearchCriteria> params);

    E findById(Long id);

    E create(E object);

    void deleteById(Long id);
}
