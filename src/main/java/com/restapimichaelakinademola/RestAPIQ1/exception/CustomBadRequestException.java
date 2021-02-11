/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restapimichaelakinademola.RestAPIQ1.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Michael.Akin-Ademola
 */
@Getter
@Setter
public class CustomBadRequestException extends RuntimeException {
    private String message;

    public CustomBadRequestException(String message) {
       super(message); 
       this.message = message;
    }    
}
