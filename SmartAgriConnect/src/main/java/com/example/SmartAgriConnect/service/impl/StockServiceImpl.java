package com.example.SmartAgriConnect.service.impl;

import com.example.SmartAgriConnect.DTO.Reponse.StockReponseDto;
import com.example.SmartAgriConnect.DTO.Request.StockRequestDto;
import com.example.SmartAgriConnect.exception.AccessDeniedException;
import com.example.SmartAgriConnect.exception.EntityNotFoundException;
import com.example.SmartAgriConnect.exception.InvalidEntityException;
import com.example.SmartAgriConnect.model.*;
import com.example.SmartAgriConnect.repository.ProduitRepository;
import com.example.SmartAgriConnect.repository.StockRepository;
import com.example.SmartAgriConnect.security.SecurityUtils;
import com.example.SmartAgriConnect.service.StockService;
import com.example.SmartAgriConnect.validator.StockValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class StockServiceImpl implements StockService {
    @Autowired
    public StockRepository stockRepository;
    @Autowired
    public SecurityUtils securityUtils;
    @Autowired
    public ProduitRepository produitRepository;


    @Override
    public StockReponseDto save(StockRequestDto dto) {

        List<String> errors = StockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Stock non valide: {}", errors);
            throw new InvalidEntityException("Stock non valide");
        }

        Produit produit=produitRepository.findById(UUID.fromString(dto.getProduitId()))
                .orElseThrow(()->
                        new EntityNotFoundException("le produit n'existe pas"));

        if (!securityUtils.getAgriculteurId().equals(produit.getAgriculteur().getId())) {
            throw new AccessDeniedException("Le produit n'appartient pas à l'agriculteur");
        }

        if (stockRepository.existsByProduitId(UUID.fromString((String.valueOf(produit.getId()))))) {
            throw new InvalidEntityException("Un stock existe déjà pour ce produit");
        }

            dto.setStatut(calculStatut(dto.getQuantiteDisponible(),dto.getSeuilCritique()));

        Stock stock = StockRequestDto.toEntity(dto);


        stock.setProduit(produit);
        stock.setAgriculteur(produit.getAgriculteur());


        Stock saved = stockRepository.save(stock);

        return StockReponseDto.fromEntity(saved);
        }

        @Override
        public StockReponseDto findId(UUID id) {
            if (id == null ) {
                throw new InvalidEntityException("L'id du stock est invalide");
            }

            Stock stock=stockRepository.findById(id)
                    .orElseThrow(()->
                            new EntityNotFoundException("le stock n'existe pas"));

            if (!securityUtils.getAgriculteurId().equals(stock.getAgriculteur().getId())
                    && !securityUtils.getRoles().equals("ADMIN")) {
                throw new AccessDeniedException("Accès refusé pour modifier ce stock");
            }


            return StockReponseDto.fromEntity(stock);

        }

        @Override
        public StockReponseDto update(UUID id, StockRequestDto dto) {

            if (id == null) {
                throw new InvalidEntityException("L'id du stock est invalide");
            }


            Stock stock=stockRepository.findById(id)
                    .orElseThrow(()->
                            new EntityNotFoundException("le stock n'existe pas"));

            if (!securityUtils.getAgriculteurId().equals(stock.getAgriculteur().getId())
                    && !securityUtils.getRoles().equals("ADMIN")) {
                throw new AccessDeniedException("Accès refusé pour modifier ce stock");
            }


            List<String> error= StockValidator.validate(dto);
            if (!error.isEmpty()){
                log.error("veuiller renseigner les valeurs");
            }

            stock.setQuantiteDisponible(dto.getQuantiteDisponible());
            stock.setSeuilCritique(dto.getSeuilCritique());
            stock.setUnite(dto.getUnite());


            stock.setStatut(calculStatut(dto.getQuantiteDisponible(), dto.getSeuilCritique()));

            Stock updated = stockRepository.save(stock);

            return StockReponseDto.fromEntity(updated);
        }

        @Override
        public void delete(UUID id) {
            if (id == null ) {
                throw new InvalidEntityException("L'id du stock est invalide");
            }

            Stock stock=stockRepository.findById(id)
                    .orElseThrow(()->
                            new EntityNotFoundException("le stock n'existe pas"));

            if (!securityUtils.getAgriculteurId().equals(stock.getAgriculteur().getId())
                    && !securityUtils.getRoles().equals("ADMIN")) {
                throw new AccessDeniedException("Accès refusé pour supprimer ce stock");
            }

            stockRepository.delete(stock);
        }


        @Override
        public StatutStock calculStatut(Integer quantite, Integer seuilCritique) {
            if (quantite==0){
                return StatutStock.RUPTURE;

            } else if (quantite<=seuilCritique) {
                return StatutStock.CRITIQUE;

            }else{
                return StatutStock.NORMAL;

            }


        }
    }




