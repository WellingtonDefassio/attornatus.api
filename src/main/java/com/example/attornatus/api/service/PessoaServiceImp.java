package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.NameAlreadyExistsException;
import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequest;
import com.example.attornatus.api.repositories.PessoaRepository;
import com.example.attornatus.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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
    public Pessoa create(PessoaRequest pessoaRequest) {
        LocalDate dataNascimento = DateUtil.stringToDate(pessoaRequest.getDataNascimento());
        validateUnique(pessoaRequest);
        Pessoa pessoa = Pessoa.builder()
                .id(pessoaRequest.getId())
                .nome(pessoaRequest.getNome())
                .dataNascimento(dataNascimento)
                .build();

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(PessoaRequest pessoaRequest) {
        findById(pessoaRequest.getId());
        return create(pessoaRequest);
    }

    @Override
    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    private void validateUnique(PessoaRequest pessoaRequest) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findByNome(pessoaRequest.getNome());
        if (optionalPessoa.isPresent() && optionalPessoa.get().getId() != pessoaRequest.getId() ) {
            throw new NameAlreadyExistsException("nome já cadastrado");
        }
    }
}
