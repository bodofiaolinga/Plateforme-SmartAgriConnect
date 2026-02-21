package com.example.SmartAgriConnect.controller;

import com.example.SmartAgriConnect.DTO.Reponse.ProduitReponseDto;
import com.example.SmartAgriConnect.DTO.Request.ProduitRequestDto;
import com.example.SmartAgriConnect.service.ProduitService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
@NoArgsConstructor
@AllArgsConstructor
public class ProduitController {

    @Autowired
    public  ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitReponseDto> save(@RequestBody ProduitRequestDto dto){
        return ResponseEntity.ok(produitService.save(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitReponseDto> find(@PathVariable UUID id){
        return ResponseEntity.ok(produitService.findId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProduitReponseDto>update(@PathVariable UUID id, ProduitRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(produitService.update(id,dto));
    }
    @PatchMapping("/{id}/desactive")
    public  ResponseEntity<String>delete(@PathVariable UUID id) throws AccessDeniedException {
        produitService.delete(id);
        return ResponseEntity.ok("produit suprimer avec success");
    }
}
