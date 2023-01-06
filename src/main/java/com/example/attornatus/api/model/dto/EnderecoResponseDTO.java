package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.PessoaEndereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoResponseDTO {
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean isPrincipal;



    public static EnderecoResponseDTO fromModel(PessoaEndereco pessoaEndereco) {
        return EnderecoResponseDTO.builder()
                .id(pessoaEndereco.getEndereco().getId())
                .logradouro(pessoaEndereco.getEndereco().getLogradouro())
                .cep(pessoaEndereco.getEndereco().getCep())
                .numero(pessoaEndereco.getEndereco().getNumero())
                .cidade(pessoaEndereco.getEndereco().getCidade())
                .isPrincipal(pessoaEndereco.getIsPrincipal())
                .build();
    }
}
