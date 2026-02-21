package com.example.SmartAgriConnect.repository;

import com.example.SmartAgriConnect.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface StockRepository extends JpaRepository<Stock,UUID> {

    Optional<Stock> findById(UUID id);


    Boolean existsByProduitId( UUID produitId);


    Optional<Stock>findByProduitId( UUID produitId);


    void deleteById(UUID id);


}
