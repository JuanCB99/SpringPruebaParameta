/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.parameta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Juan
 */
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionApiResponse> manejadorExcepcionNoDisponible(Exception ex) {
        ExceptionApiResponse response = new ExceptionApiResponse("Error interno del servidor", "error-503", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
}
