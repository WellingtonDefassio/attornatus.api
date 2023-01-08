package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PessoaRequest {

    private Long id;
    @NotBlank(message = "o campo nome é obrigatorio!")
    private String nome;
    @NotBlank(message = "o campo dataNascimento é obrigatorio! pattern 'dd-mm-yyyy'")
    private String dataNascimento;

}
