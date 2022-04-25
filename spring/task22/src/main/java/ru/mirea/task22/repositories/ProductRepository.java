package ru.mirea.task22.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task22.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
