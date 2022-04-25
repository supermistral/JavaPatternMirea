package ru.mirea.task21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task21.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
