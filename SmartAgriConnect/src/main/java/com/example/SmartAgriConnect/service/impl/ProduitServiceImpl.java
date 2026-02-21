package com.example.SmartAgriConnect.service.impl;
import com.example.SmartAgriConnect.DTO.Reponse.ProduitReponseDto;
import com.example.SmartAgriConnect.DTO.Request.ProduitRequestDto;
import com.example.SmartAgriConnect.exception.EntityNotFoundException;

import com.example.SmartAgriConnect.exception.InvalidEntityException;
import com.example.SmartAgriConnect.exception.InvalidNotFoundException;
import com.example.SmartAgriConnect.model.Agriculteur;
import com.example.SmartAgriConnect.model.Produit;
import com.example.SmartAgriConnect.repository.AgriculteurRepository;
import com.example.SmartAgriConnect.repository.ProduitRepository;
import com.example.SmartAgriConnect.security.SecurityUtils;
import com.example.SmartAgriConnect.service.ProduitService;
import com.example.SmartAgriConnect.validator.ProduitValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    public ProduitRepository produitRepository;

    @Autowired
    public SecurityUtils securityUtils;
    @Autowired
    public AgriculteurRepository agriculteurRepository;
    @Override
    public ProduitReponseDto save(ProduitRequestDto dto) {
        List<String>errors= ProduitValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("entrer les informations du produit");
            throw new InvalidNotFoundException("Produit invalide");
        }

        Produit produit=new Produit();
        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setCategories(dto.getCategories());
        produit.setUniteMesure(dto.getUniteMesure());
        produit.setPrixVente(dto.getPrixVente());



        Agriculteur agriculteur = agriculteurRepository.findByEmail(securityUtils.getEmail())
                .orElseThrow(()->
                        new EntityNotFoundException("agriculteur non trouver"));

        produit.setAgriculteur(agriculteur);



        return ProduitReponseDto.fromEntity(
                produitRepository.save(produit)
        );

    }

    @Override
    public ProduitReponseDto findId(UUID id) {

        if (id==null){
            log.error("entrer l'id");
            throw new InvalidEntityException("L'id ne peut pas être null");

        }

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé"));

        return ProduitReponseDto.fromEntity(produit);
    }

    @Override
    public ProduitReponseDto update(UUID id, ProduitRequestDto dto) {

        if (id==null){
            log.error("entrer l'id");
            return null;
        }

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé"));

        List<String> errors = ProduitValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Produit invalide pour update {}", errors);
            throw new InvalidEntityException("Données invalides pour la mise à jour");
        }
        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setCategories(dto.getCategories());
        produit.setUniteMesure(dto.getUniteMesure());
        produit.setPrixVente(dto.getPrixVente());

        return ProduitReponseDto.fromEntity(
                produitRepository.save(produit)
        );
    }

    @Override
    public void delete(UUID id) {

        if (id==null){
            log.error("entrer l'id");
            throw new InvalidEntityException("L'id ne peut pas être null");
        }

        produitRepository.deleteById(id);

    }
}
