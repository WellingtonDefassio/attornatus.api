package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequestDTO;

public interface PessoaService {

    Pessoa findById(Long id);
    Pessoa create(PessoaRequestDTO pessoaRequestDTO);
    Pessoa update(PessoaRequestDTO pessoaRequestDTO);
}
