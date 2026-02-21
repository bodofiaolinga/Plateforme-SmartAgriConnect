package com.example.SmartAgriConnect.controller;


import com.example.SmartAgriConnect.DTO.Reponse.StockReponseDto;
import com.example.SmartAgriConnect.DTO.Request.StockRequestDto;
import com.example.SmartAgriConnect.service.StockService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
@NoArgsConstructor
@AllArgsConstructor
public class StockController {


    @Autowired
    public  StockService stockService;

    @PostMapping
    public ResponseEntity<StockReponseDto> save(@RequestBody StockRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(stockService.save(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<StockReponseDto> find(@PathVariable UUID id){
        return ResponseEntity.ok(stockService.findId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StockReponseDto>update(@PathVariable UUID id, StockRequestDto dto) throws AccessDeniedException {
        return ResponseEntity.ok(stockService.update(id,dto));
    }
    @PatchMapping("/{id}/desactive")
    public  ResponseEntity<String>delete(@PathVariable UUID id) throws AccessDeniedException {
        stockService.delete(id);
        return ResponseEntity.ok("stock suprimer avec success");
    }
}
