package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.util.DateUtil;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PessoaResponseDTO {
    private Long id;
    private String nome;
    private String dataNascimento;
    private List<EnderecoResponseDTO> enderecos;


    public static PessoaResponseDTO fromModel(Pessoa pessoa) {

        return PessoaResponseDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(DateUtil.stringFormat(pessoa.getDataNascimento()))
                .enderecos(pessoa.getEnderecos().stream().map(e -> EnderecoResponseDTO.fromModel(e)).collect(Collectors.toList()))
                .build();

    }


}
