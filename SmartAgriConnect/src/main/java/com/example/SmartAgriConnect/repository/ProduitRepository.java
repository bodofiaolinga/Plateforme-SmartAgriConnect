package com.example.SmartAgriConnect.repository;

import com.example.SmartAgriConnect.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, UUID> {
    Optional<Produit> findById(UUID id);

    void deleteById(UUID id);
}
