package ru.mirea.task19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task19.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
