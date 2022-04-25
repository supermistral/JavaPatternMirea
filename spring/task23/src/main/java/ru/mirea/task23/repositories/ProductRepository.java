package ru.mirea.task23.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task23.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
