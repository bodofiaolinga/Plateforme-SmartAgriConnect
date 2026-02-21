package com.example.SmartAgriConnect.validator;

import com.example.SmartAgriConnect.DTO.Reponse.CommandeReponseDto;
import com.example.SmartAgriConnect.DTO.Request.CommandeRequestDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public  class CommandeValidator {


    public static List<String> validate(CommandeRequestDto dto){

        List<String> errors=new ArrayList<>();

        if (dto==null){
            errors.add("ajoueter la quantiter");
        }



        if (dto.getQuantiteCommandee()==null){
            errors.add("ajoueter la quantiter");
        }

        return errors;

    }

    
}
