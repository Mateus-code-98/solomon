package com.solomon.solomon.shared.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solomon.solomon.shared.services.CepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping()
public class SharedController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<?> getCepDetails(@PathVariable String cep) {
        return ResponseEntity.ok(this.cepService.getCepDetails(cep));
    }

}
