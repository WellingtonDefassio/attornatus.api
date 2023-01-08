package com.example.attornatus.api.service;

import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;
import com.example.attornatus.api.model.dto.SetPrincipalRequest;

public interface PessoaEnderecoService {


    PessoaEndereco create(Long id, EnderecoRequest enderecoRequest);


    PessoaEndereco atualizaPrincipal(SetPrincipalRequest principalRequest);
}
