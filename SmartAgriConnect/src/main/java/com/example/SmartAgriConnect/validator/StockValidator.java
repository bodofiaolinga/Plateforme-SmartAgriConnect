package com.example.SmartAgriConnect.validator;

import com.example.SmartAgriConnect.DTO.Reponse.StockReponseDto;
import com.example.SmartAgriConnect.DTO.Request.StockRequestDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StockValidator {

    public static List<String> validate(StockRequestDto dto) {
        List<String> errors=new ArrayList<>();

        if (dto==null){
            errors.add("entrer la quantite");
            errors.add("entrer le seuil critique");
            errors.add("entrer la derniere mise A Jour ");
            errors.add("entrer le statut");
            errors.add("entrer l'unite'");
        }

        if (dto.getQuantiteDisponible()==null){
            errors.add("entrer la quantite ");
        }
        if (dto.getQuantiteDisponible()<0){
            errors.add("entrer la quantite supperieur a zero");
        }
        if (dto.getSeuilCritique()==null){
            errors.add("entrer le seuil critique");
        }

        if (!StringUtils.hasLength(dto.getUnite())){
            errors.add("entrer l'unite'");
        }

        return errors;
    }
}
