package com.example.SmartAgriConnect.controller;

import com.example.SmartAgriConnect.DTO.Reponse.CommandeReponseDto;
import com.example.SmartAgriConnect.DTO.Request.CommandeRequestDto;
import com.example.SmartAgriConnect.service.CommandeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;
@RestController
@RequestMapping("/api/commandes")
@NoArgsConstructor
@AllArgsConstructor
public class CommandeController {

     @Autowired
    public  CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeReponseDto> save(@RequestBody CommandeRequestDto dto){
        return ResponseEntity.ok(commandeService.save(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeReponseDto> find(@PathVariable UUID id){
        return ResponseEntity.ok(commandeService.findId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommandeReponseDto>update(@PathVariable UUID id, CommandeRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(commandeService.update(id,dto));
    }
    @PatchMapping("/{id}/desactive")
    public  ResponseEntity<String>delete(@PathVariable UUID id) throws AccessDeniedException {
        commandeService.delete(id);
        return ResponseEntity.ok("commmande suprimer avec success");
    }


}
