package com.sisgestionprod.cl.sis_gestion_prod.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<?>manejarError(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
