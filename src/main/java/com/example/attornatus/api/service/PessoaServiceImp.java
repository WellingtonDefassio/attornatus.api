package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImp implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public Pessoa findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("pessoa não cadastrada"));
        
        return pessoa;
    }
}
