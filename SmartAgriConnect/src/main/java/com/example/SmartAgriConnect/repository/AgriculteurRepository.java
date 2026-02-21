package com.example.SmartAgriConnect.repository;


import com.example.SmartAgriConnect.model.Agriculteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AgriculteurRepository extends JpaRepository<Agriculteur,UUID> {
    @Override
    Optional<Agriculteur> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);

    Optional<Agriculteur> findByEmail(String email);


}
