package com.example.SmartAgriConnect.service;

import com.example.SmartAgriConnect.DTO.Reponse.AgriculteurReponseDto;
import com.example.SmartAgriConnect.DTO.Request.AgriculteurRequestDto;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

public interface AgriculteurService {

    AgriculteurReponseDto save(AgriculteurRequestDto dto);

    AgriculteurReponseDto findId(UUID id);

    AgriculteurReponseDto update(UUID id, AgriculteurRequestDto dto) throws AccessDeniedException;

    void delete(UUID id) throws AccessDeniedException;
}
