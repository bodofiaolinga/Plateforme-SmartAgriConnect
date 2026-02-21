package com.example.SmartAgriConnect.handler;

import com.example.SmartAgriConnect.exception.ErrorsCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class ErrorDto {

    public Integer httpCode;

    public ErrorsCode code;

    public String message;

    public List<String> errors;
}
