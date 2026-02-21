package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.Reponse.CommandeReponseDto;
import com.example.SmartAgriConnect.DTO.Request.CommandeRequestDto;

import java.util.UUID;

public interface CommandeService {

    CommandeReponseDto save(CommandeRequestDto dto);

    CommandeReponseDto findId(UUID id);

    CommandeReponseDto update(UUID id, CommandeRequestDto dto);

    void delete(UUID id);

    Integer prixTotal(Integer quantite, Integer prix);
}
