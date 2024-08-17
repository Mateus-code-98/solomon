package com.solomon.solomon.shared.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.solomon.solomon.shared.dtos.CepResultDTO;
import com.solomon.solomon.shared.exceptions.AppExpection;

@Service
public class CepService {
    private final String CEP_API_URL = "https://viacep.com.br/ws/{cep}/json/";

    private String validateCep(String cep) {
        if (cep == null)
            cep = "";

        cep = cep.replaceAll("[^0-9]", "");

        if (cep.length() != 8) {
            throw new AppExpection("CEP inválido", 400);
        }

        return cep;
    }

    public CepResultDTO getCepDetails(String cep) {
        cep = validateCep(cep);

        String url = CEP_API_URL.replace("{cep}", cep);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CepResultDTO> result = restTemplate.getForEntity(url, CepResultDTO.class);

        return result.getBody();
    }
}
