package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;

import java.util.Optional;

public interface EnderecoService {

    Endereco create(EnderecoRequest enderecoRequest);
    Optional<Endereco> findByCepENumero(EnderecoRequest enderecoRequest);

    Endereco findById(Long idEndereco);
}
