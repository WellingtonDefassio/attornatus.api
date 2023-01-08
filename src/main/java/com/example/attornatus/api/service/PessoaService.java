package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaService {

    Pessoa findById(Long id);
    Pessoa create(PessoaRequest pessoaRequest);
    Pessoa update(PessoaRequest pessoaRequest);
    Page<Pessoa> findAll(Pageable pageable);

}
