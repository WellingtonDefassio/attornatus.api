package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;
import com.example.attornatus.api.model.dto.SetPrincipalRequest;
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
    public PessoaEndereco create(Long id, EnderecoRequest enderecoRequest) {
        Pessoa pessoa = pessoaService.findById(id);
        Endereco endereco = enderecoService.create(enderecoRequest);
        PessoaEndereco pessoaEndereco = build(pessoa, endereco);
        return pessoaEnderecoRepository.save(pessoaEndereco);
    }

    @Override
    public PessoaEndereco atualizaPrincipal(SetPrincipalRequest principalRequest) {
        Pessoa pessoa = pessoaService.findById(principalRequest.getIdPessoa());
        Endereco endereco = enderecoService.findById(principalRequest.getIdEndereco());
        validaPessoaEndereco(pessoa, endereco);
        setAllNoPrincipal(pessoa);
        return pessoaEnderecoRepository.save(build(pessoa, endereco, true));


    }

    private void setAllNoPrincipal(Pessoa pessoa) {
        pessoa.getEnderecos().stream().forEach(e -> {
            if (e.getIsPrincipal() == true) {
                e.setIsPrincipal(false);
            }
        });
    }

    private void validaPessoaEndereco(Pessoa pessoa, Endereco endereco) {
      if(!pessoa.getEnderecos().stream().anyMatch(p -> p.getEndereco().getId() == endereco.getId())){
          throw new ResourceNotFoundException("pessoa não possui o endereço informado");
      }
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

    private PessoaEndereco build(Pessoa pessoa, Endereco endereco, Boolean isPrincipal) {
        PessoaEnderecoChave enderecoChave = PessoaEnderecoChave
                .builder()
                .enderecoId(endereco.getId())
                .pessoaId(pessoa.getId())
                .build();

        return PessoaEndereco.builder()
                .id(enderecoChave)
                .pessoa(pessoa)
                .endereco(endereco)
                .isPrincipal(isPrincipal)
                .build();
    }
}
