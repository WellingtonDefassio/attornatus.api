package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.PessoaEndereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoResponse {
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean isPrincipal;



    public static EnderecoResponse fromModel(PessoaEndereco pessoaEndereco) {

        return EnderecoResponse.builder()
                .id(pessoaEndereco.getEndereco().getId())
                .logradouro(pessoaEndereco.getEndereco().getLogradouro())
                .cep(pessoaEndereco.getEndereco().getCep())
                .numero(pessoaEndereco.getEndereco().getNumero())
                .cidade(pessoaEndereco.getEndereco().getCidade())
                .isPrincipal(pessoaEndereco.getIsPrincipal())
                .build();
    }
}
