package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PessoaService {

    Pessoa findById(Long id);
    Pessoa create(PessoaRequestDTO pessoaRequestDTO);
    Pessoa update(PessoaRequestDTO pessoaRequestDTO);
    Page<Pessoa> findAll(Pageable pageable);
}
