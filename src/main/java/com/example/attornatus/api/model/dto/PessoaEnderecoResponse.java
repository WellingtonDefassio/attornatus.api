package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PessoaEnderecoResponse {
    private Long id;
    private String nome;
    private String dataNascimento;
    private List<EnderecoResponse> enderecos;


    public static PessoaEnderecoResponse fromModel(Pessoa pessoa) {

        return PessoaEnderecoResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(DateUtil.stringFormat(pessoa.getDataNascimento()))
                .enderecos(pessoa.getEnderecos()
                        .stream()
                        .map(e -> EnderecoResponse.fromModel(e))
                        .collect(Collectors.toList()))
                .build();

    }


    public static Page<PessoaEnderecoResponse> fromModels(Page<Pessoa> pessoas) {
        return pessoas.map(PessoaEnderecoResponse::fromModel);
    }
}
