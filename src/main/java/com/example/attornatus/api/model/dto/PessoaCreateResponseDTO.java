package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.util.DateUtil;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PessoaCreateResponseDTO {
    private Long id;
    private String nome;
    private String dataNascimento;


    public static PessoaCreateResponseDTO fromModel(Pessoa pessoa) {

        return PessoaCreateResponseDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(DateUtil.stringFormat(pessoa.getDataNascimento()))
                .build();
    }


}
