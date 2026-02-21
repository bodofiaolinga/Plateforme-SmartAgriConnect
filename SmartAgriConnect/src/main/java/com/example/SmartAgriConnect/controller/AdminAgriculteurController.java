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
@RequestMapping("/api/admin/agriculteurs")
@NoArgsConstructor
@AllArgsConstructor
public class AdminAgriculteurController {
    @Autowired
    public  AgriculteurService agriculteurService;
    @PostMapping
    public ResponseEntity<AgriculteurReponseDto> save(@RequestBody AgriculteurRequestDto dto){
        return ResponseEntity.ok(agriculteurService.save(dto));

    }


    @PatchMapping("/{id}/desactive")
    public  ResponseEntity<String>delete(@PathVariable UUID id) throws AccessDeniedException {
        agriculteurService.delete(id);
        return ResponseEntity.ok("Agriculteur suprimer avec success");
    }
}
