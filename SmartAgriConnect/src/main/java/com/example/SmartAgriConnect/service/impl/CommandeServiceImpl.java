package com.example.SmartAgriConnect.service.impl;

import com.example.SmartAgriConnect.DTO.Reponse.CommandeReponseDto;
import com.example.SmartAgriConnect.DTO.Request.CommandeRequestDto;
import com.example.SmartAgriConnect.exception.AccessDeniedException;
import com.example.SmartAgriConnect.exception.CommandeDejaValideeException;
import com.example.SmartAgriConnect.exception.EntityNotFoundException;
import com.example.SmartAgriConnect.exception.InvalidNotFoundException;
import com.example.SmartAgriConnect.model.*;
import com.example.SmartAgriConnect.repository.AgriculteurRepository;
import com.example.SmartAgriConnect.repository.CommandeRepository;
import com.example.SmartAgriConnect.repository.ProduitRepository;
import com.example.SmartAgriConnect.repository.StockRepository;
import com.example.SmartAgriConnect.security.SecurityUtils;
import com.example.SmartAgriConnect.service.CommandeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CommandeServiceImpl implements CommandeService {

     @Autowired
     public ProduitRepository produitRepository;

    @Autowired
    public CommandeRepository commandeRepository;

    @Autowired
    public StockRepository stockRepository;

    @Autowired
    public SecurityUtils securityUtils;

    @Autowired
    public AgriculteurRepository agriculteurRepository;
    @Override
    @Transactional
    public CommandeReponseDto save(CommandeRequestDto dto) {


        Produit produit = produitRepository.findById(UUID.fromString(dto.getProduitId()))
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé"));


        Stock stock = stockRepository.findByProduitId(produit.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stock du produit non trouvé"));


        if (dto.getQuantiteCommandee() > stock.getQuantiteDisponible()) {
            throw new RuntimeException("Stock insuffisant pour le produit " + produit.getNom());
        }


        UUID agriculteurId = securityUtils.getAgriculteurId();
        Agriculteur agriculteur = agriculteurRepository.findById(agriculteurId)
                .orElseThrow(() -> new EntityNotFoundException("Agriculteur non trouvé"));


        stock.setQuantiteDisponible(stock.getQuantiteDisponible() - dto.getQuantiteCommandee());

        stockRepository.save(stock);


        Commande commande = CommandeRequestDto.toEntity(dto);
        commande.setProduit(produit);
        commande.setAgriculteur(agriculteur);
        commande.setStatus(String.valueOf(StatutCommande.EN_COURS));
        commande.setPrixTotal(prixTotal(dto.getQuantiteCommandee(), produit.getPrixVente()));


        if (!commande.getAgriculteur().getId().equals(securityUtils.getAgriculteurId())) {
            throw new AccessDeniedException("L'utilisateur n'a pas les accès");
        }


        return CommandeReponseDto.fromEntity(
                commandeRepository.save(commande)
        );
    }


    @Override
    public CommandeReponseDto findId(UUID id) {

        if (id==null){
            log.error("l'id est vide");
            throw new InvalidNotFoundException("l'id ne peut etre null");
        }


        Commande commande=commandeRepository.findById(id)
                .orElseThrow( ()->
                        new EntityNotFoundException("commande non trouver"));

        if (LocalDateTime.now().isAfter(commande.getCreationDate().plusMinutes(5))){
            commande.setStatus(String.valueOf(StatutCommande.VALIDEE));

        }


        if (securityUtils.getRoles().contains("ADMIN")){
            return CommandeReponseDto.fromEntity(commande);
        }

         if (!securityUtils.getAgriculteurId().equals(commande.getAgriculteur().getId())) {
             throw new AccessDeniedException("ces commandes ne vous appartienne pas vous n'avez pas l'acces");
         }


        return CommandeReponseDto.fromEntity(commande);
    }

    @Override
    @Transactional
    public CommandeReponseDto update(UUID id, CommandeRequestDto dto) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée"));


        if (!commande.getStatus().equals(StatutCommande.EN_COURS.name())) {
            throw new CommandeDejaValideeException("L’état de la commande ne permet pas cette opération");
        }


        if (LocalDateTime.now().isAfter(commande.getCreationDate().plusMinutes(5))) {
            commande.setStatus(StatutCommande.VALIDEE.name());
            return CommandeReponseDto.fromEntity(commandeRepository.save(commande));
        }

        int ancienneQuantite = commande.getQuantiteCommandee();
        int nouvelleQuantite = dto.getQuantiteCommandee();
        int difference = nouvelleQuantite - ancienneQuantite;

        Stock stock = commande.getProduit().getStock();

        // Si augmentation
        if (difference > 0) {
            if (stock.getQuantiteDisponible() < difference) {
                throw new IllegalArgumentException("Stock insuffisant");
            }
            stock.setQuantiteDisponible(stock.getQuantiteDisponible() - difference);
        }

        // Si diminution
        if (difference < 0) {
            stock.setQuantiteDisponible(stock.getQuantiteDisponible() + Math.abs(difference));
        }

        //  Mise à jour de la commande
        commande.setQuantiteCommandee(nouvelleQuantite);
        commande.setPrixTotal(
                prixTotal(nouvelleQuantite, commande.getProduit().getPrixVente())
        );

        return CommandeReponseDto.fromEntity(
                commandeRepository.save(commande)
        );
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (id==null){
            log.error("l'id ne peut pas etre null");
            throw new InvalidNotFoundException("l'id ne peut etre null");
        }

        Commande commande =commandeRepository.findById(id)
                .orElseThrow( ()->
                        new EntityNotFoundException("commande non trouver"));

        if (LocalDateTime.now().isAfter(commande.getCreationDate().plusMinutes(5))){
            commande.setStatus(String.valueOf(StatutCommande.VALIDEE));

        }

        if (!securityUtils.getAgriculteurId().equals(commande.getAgriculteur().getId())) {
            throw new AccessDeniedException("l'utilisateur n'a pas les acces ");
        }

        if (commande.getStatus().equals("EN_COURS")){
            commande.setStatus(String.valueOf(StatutCommande.ANNULEE));

        }else {
            throw new CommandeDejaValideeException("L’état de la commande ne permet pas cette opération");
        }
        Stock stock=commande.getStock();

        stock.setQuantiteDisponible(stock.getQuantiteDisponible()+commande.getQuantiteCommandee());

        stockRepository.save(stock);
        commandeRepository.save(commande);
    }

    @Override
    public Integer prixTotal(Integer quantite, Integer prix) {
        return quantite*prix ;
    }
}
