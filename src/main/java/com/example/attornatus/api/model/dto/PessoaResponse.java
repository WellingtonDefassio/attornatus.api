package com.example.attornatus.api.model.dto;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class PessoaResponse {
    private Long id;
    private String nome;
    private String dataNascimento;


    public static PessoaResponse fromModel(Pessoa pessoa) {

        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(DateUtil.stringFormat(pessoa.getDataNascimento()))
                .build();
    }
    public static Page<PessoaResponse> fromModels(Page<Pessoa> pessoas) {
        return pessoas.map(PessoaResponse::fromModel);
    }
}
