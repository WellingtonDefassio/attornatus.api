package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequestDTO;
import com.example.attornatus.api.repositories.PessoaRepository;
import com.example.attornatus.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Override
    public Pessoa create(PessoaRequestDTO pessoaRequestDTO) {
        LocalDate dataNascimento = DateUtil.stringToDate(pessoaRequestDTO.getDataNascimento());
        Pessoa pessoa = Pessoa.builder()
                .id(pessoaRequestDTO.getId())
                .nome(pessoaRequestDTO.getNome())
                .dataNascimento(dataNascimento)
                .build();

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(PessoaRequestDTO pessoaRequestDTO) {
        findById(pessoaRequestDTO.getId());
        return create(pessoaRequestDTO);
    }
}
