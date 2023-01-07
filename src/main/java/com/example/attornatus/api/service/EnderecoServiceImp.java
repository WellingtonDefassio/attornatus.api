package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;
import com.example.attornatus.api.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImp implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public Endereco create(EnderecoRequestDTO requestDTO) {
        Endereco.validaCep(requestDTO.getCep());
        Optional<Endereco> enderecoOptional = findByCepENumero(requestDTO);
        if (enderecoOptional.isPresent()) {
            return enderecoOptional.get();
        }
        Endereco endereco = Endereco.builder()
                .cidade(requestDTO.getCidade())
                .logradouro(requestDTO.getLogradouro())
                .numero(requestDTO.getNumero())
                .cep(requestDTO.getCep())
                .build();

        return enderecoRepository.save(endereco);
    }

    @Override
    public Optional<Endereco> findByCepENumero(EnderecoRequestDTO enderecoRequestDTO) {
        return enderecoRepository.findByCepAndNumero(enderecoRequestDTO.getCep(), enderecoRequestDTO.getNumero());
    }


}
