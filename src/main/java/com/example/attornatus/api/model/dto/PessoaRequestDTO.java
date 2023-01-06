package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PessoaRequestDTO {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String dataNascimento;

}
