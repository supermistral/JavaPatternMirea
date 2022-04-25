package ru.mirea.task16.controllers;

import org.hibernate.Session;
import ru.mirea.task16.models.IndexedModel;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseController<E extends IndexedModel> {

    @PersistenceContext
    private EntityManager entityManager;

    protected Session session = null;

    @PostConstruct
    void init() {
        session = entityManager.unwrap(Session.class);
    }

    public abstract List<?> getAll();

    public abstract E getOneById(Long id);

    public abstract void createOne(E object);

    public abstract void deleteOne(Long id);
}
