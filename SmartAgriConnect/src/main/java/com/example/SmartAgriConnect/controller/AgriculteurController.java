package com.example.SmartAgriConnect.controller;
import com.example.SmartAgriConnect.DTO.Reponse.AgriculteurReponseDto;
import com.example.SmartAgriConnect.DTO.Request.AgriculteurRequestDto;
import com.example.SmartAgriConnect.service.AgriculteurService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;


@RestController
@RequestMapping("/api/agriculteur")
@NoArgsConstructor
@AllArgsConstructor
public class AgriculteurController {
    @Autowired
    public  AgriculteurService agriculteurService;


   @GetMapping("/{id}")
    public ResponseEntity<AgriculteurReponseDto> find(@PathVariable UUID id){
        return ResponseEntity.ok(agriculteurService.findId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AgriculteurReponseDto>update(@PathVariable UUID id,AgriculteurRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(agriculteurService.update(id,dto));
    }




}
