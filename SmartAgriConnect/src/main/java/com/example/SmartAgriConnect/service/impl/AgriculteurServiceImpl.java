package com.example.SmartAgriConnect.service.impl;
import com.example.SmartAgriConnect.DTO.Reponse.AgriculteurReponseDto;
import com.example.SmartAgriConnect.DTO.Request.AgriculteurRequestDto;
import com.example.SmartAgriConnect.exception.*;
import com.example.SmartAgriConnect.model.Agriculteur;
import com.example.SmartAgriConnect.model.EtatCompte;
import com.example.SmartAgriConnect.repository.AgriculteurRepository;
import com.example.SmartAgriConnect.security.SecurityUtils;
import com.example.SmartAgriConnect.service.AgriculteurService;
import com.example.SmartAgriConnect.validator.AgriculteurValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AgriculteurServiceImpl implements AgriculteurService {

    @Autowired
    public  AgriculteurRepository agriculteurRepository;

    @Autowired
    public SecurityUtils securityUtils;


    @Override
    public AgriculteurReponseDto save(AgriculteurRequestDto dto) {

        List<String> errors= AgriculteurValidator.validate(dto);
        if (!errors.isEmpty()){
          log.error("l'agriculteur  n'est pas valide");
          throw new InvalidNotFoundException("entrer les informations de l'agriculteur", ErrorsCode.AGRICULTEUR_NOT_FOUND,errors);
        }


        return AgriculteurReponseDto.fromEntity(
                agriculteurRepository.save(
                        AgriculteurRequestDto.toEntity(dto)
                )
        );
    }

    @Override
    public AgriculteurReponseDto findId(UUID id) {

        if (id==null){
            log.error("entrer l'id");
            return null;
        }

        if(agriculteurRepository.getReferenceById(id).getEtatCompte()==EtatCompte.SUPPRIMER) {

            throw new EntityNotFoundException("utilisateur suprimer", ErrorsCode.AGRICULTEUR_NOT_FOUND);
        }
        Optional<Agriculteur>agriculteur=agriculteurRepository.findById(id);

        if (!agriculteur.isPresent()) throw new EntityNotFoundException("l'utilisateur n'existe pas");

        AgriculteurReponseDto dto= AgriculteurReponseDto.fromEntity(agriculteur.get());


        return Optional.of(dto)
                .orElseThrow(()->new EntityNotFoundException("aucun agriculteur trouver avec l'id",ErrorsCode.AGRICULTEUR_NOT_FOUND));



    }

    @Override
    public AgriculteurReponseDto update(UUID id, AgriculteurRequestDto dto) throws AccessDeniedException {

        List<String> errors= AgriculteurValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("vueiller renseigner les valeur");

        }

        if (id==null){
            log.error("entrer l'id de l'agriculteur");
            return null;
        } else if (!agriculteurRepository.existsById(id) || agriculteurRepository.getReferenceById(id).getEtatCompte()==EtatCompte.SUPPRIMER) {
            throw new EntityNotFoundException("agriculteur non trouver ou compte supprimer",ErrorsCode.AGRICULTEUR_NOT_FOUND);
        }

        Agriculteur agriculteur = agriculteurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Agriculteur introuvable avec l'id : " + id,
                        ErrorsCode.AGRICULTEUR_NOT_FOUND
                ));

        if (!securityUtils.getAgriculteurId().equals(id)){

           throw new AccessDeniedException("access refuser: impossible de modifier ");
        }

        if (!agriculteur.getEtatCompte().equals(EtatCompte.ACTIF)){
            throw new InvalidOperationException("la mise a jour ne peut etre effectuer",ErrorsCode.AGRICULTEUR_NOT_FOUND);
        }

        agriculteur.setNom(dto.getNom());
        agriculteur.setPrenom(dto.getPrenom());
        agriculteur.setTelephone(dto.getTelephone());
        agriculteur.setLocalisation(dto.getLocalisation());


        return AgriculteurReponseDto.fromEntity(
                agriculteurRepository.save(agriculteur)

        );
    }




        @Override
        public void delete(UUID id) throws AccessDeniedException {
        if (id==null) {
            log.error("entrer l'id");
            return;
        }
        if (!agriculteurRepository.existsById(id) || agriculteurRepository.getReferenceById(id).getEtatCompte().equals(EtatCompte.SUPPRIMER)) {
            throw new EntityNotFoundException("agriculteur A DEJA eter supprimer ou n'existe pas ",ErrorsCode.AGRICULTEUR_NOT_FOUND);
        }

        if (!securityUtils.getRoles().contains("ADMIN")) {
            throw new AccessDeniedException("Action refusée : droits insuffisants");
        }

            Agriculteur agriculteur = agriculteurRepository.findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Agriculteur introuvable",
                                    ErrorsCode.AGRICULTEUR_NOT_FOUND
                            )
                    );

            if (agriculteur.getEtatCompte() == EtatCompte.SUPPRIMER) {
                throw new EntityNotFoundException(
                        "Agriculteur déjà supprimé",
                        ErrorsCode.AGRICULTEUR_NOT_FOUND
                );
            }

            agriculteur.setEtatCompte(EtatCompte.SUPPRIMER);
            agriculteurRepository.save(agriculteur);

    }
}
