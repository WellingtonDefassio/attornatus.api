package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;

import java.util.Optional;

public interface EnderecoService {

    Endereco create(EnderecoRequestDTO enderecoRequestDTO);
    Optional<Endereco> findByCepENumero(EnderecoRequestDTO enderecoRequestDTO);
}
