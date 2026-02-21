package com.example.SmartAgriConnect.validator;

import com.example.SmartAgriConnect.DTO.Reponse.ProduitReponseDto;
import com.example.SmartAgriConnect.DTO.Request.ProduitRequestDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProduitValidator {

    public static List<String> validate(ProduitRequestDto dto) {
        List<String> errors=new ArrayList<>();

        if (dto==null){
            errors.add("entrer le mom");
            errors.add("entrer la description");
            errors.add("entrer la categories");
            errors.add("entrer l'unite de mesure");
            errors.add("entrer le prix de vente ");
        }

        if (!StringUtils.hasLength(dto.getNom())){
            errors.add("entrer le mom");
        }
        if (!StringUtils.hasLength(dto.getDescription())){
            errors.add("entrer la description");
        }
        if (!StringUtils.hasLength(dto.getCategories())){
            errors.add("entrer la categories");
        }
        if (!StringUtils.hasLength(dto.getUniteMesure())){
            errors.add("entrer l'unite de mesure");
        }
        if (dto.getPrixVente()==null){
            errors.add("entrer le prix de vente ");
        }
        return errors;
    }
}
