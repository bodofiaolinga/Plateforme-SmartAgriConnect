package com.example.SmartAgriConnect.validator;

import com.example.SmartAgriConnect.DTO.Reponse.AgriculteurReponseDto;
import com.example.SmartAgriConnect.DTO.Request.AgriculteurRequestDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AgriculteurValidator {

    public static List<String> validate(AgriculteurRequestDto dto) {
        List<String> errors=new ArrayList<>();
        if (dto==null) {
            errors.add("veuiller entrer le nom");
            errors.add("veuiller entrer le prenom");
            errors.add("veuiller entrer l' email");
            errors.add("veuiller entrer le numero de telephone");
            errors.add("veuiller entrer la localisation");
        }


        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("veuiller entrer le nom");
        }
        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("veuiller entrer le prenom");
        }
        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("veuiller entrer l' email");
        }
        if(!StringUtils.hasLength(dto.getTelephone())){
            errors.add("veuiller entrer le numero de telephone");
        }
        if(!StringUtils.hasLength(dto.getLocalisation())){
            errors.add("veuiller entrer la localisation");
        }
       return errors;
    }
}
