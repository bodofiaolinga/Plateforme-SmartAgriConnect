package com.example.SmartAgriConnect.repository;

import com.example.SmartAgriConnect.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CommandeRepository extends JpaRepository<Commande,UUID> {

    Boolean findByProduitId(UUID id);
    Optional<Commande> findById(UUID id);

}
