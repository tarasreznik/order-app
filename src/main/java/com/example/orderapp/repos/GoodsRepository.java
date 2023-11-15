package com.example.orderapp.repos;

import com.example.orderapp.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Boolean existsByName(String name);

    Optional<Goods> findByName(String name);
}
