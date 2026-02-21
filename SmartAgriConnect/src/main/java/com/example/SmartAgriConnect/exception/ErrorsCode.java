package com.example.SmartAgriConnect.exception;

public enum ErrorsCode {
    
    AGRICULTEUR_NOT_FOUND(1000),
    COMMANDE_NOT_FOUND(2000),
    PRODUIT_NOT_FOUND(3000),
    STOCK_NOT_FOUND(4000);



    private int code;

    ErrorsCode(int code) {
        this.code=code;
    }
}
