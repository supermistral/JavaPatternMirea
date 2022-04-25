package ru.mirea.task19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.task19.models.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
}
