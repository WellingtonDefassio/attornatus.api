package com.example.attornatus.api.service;

import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;
public interface PessoaEnderecoService {


    PessoaEndereco create(Long id, EnderecoRequestDTO enderecoRequestDTO);


}
