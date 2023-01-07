package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;
import com.example.attornatus.api.model.id.PessoaEnderecoChave;
import com.example.attornatus.api.repositories.PessoaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaEnderecoServiceImp implements PessoaEnderecoService {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaEnderecoRepository pessoaEnderecoRepository;


    @Override
    public PessoaEndereco create(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Pessoa pessoa = pessoaService.findById(id);
        Endereco endereco = enderecoService.create(enderecoRequestDTO);
        PessoaEndereco pessoaEndereco = build(pessoa, endereco);
        return pessoaEnderecoRepository.save(pessoaEndereco);
    }
    private PessoaEndereco build(Pessoa pessoa, Endereco endereco) {
        PessoaEnderecoChave enderecoChave = PessoaEnderecoChave
                .builder()
                .enderecoId(endereco.getId())
                .pessoaId(pessoa.getId())
                .build();

        return PessoaEndereco.builder()
                .id(enderecoChave)
                .pessoa(pessoa)
                .endereco(endereco)
                .isPrincipal(Boolean.FALSE)
                .build();
    }
}
