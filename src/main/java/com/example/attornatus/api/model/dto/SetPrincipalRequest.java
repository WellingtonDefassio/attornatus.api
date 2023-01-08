package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetPrincipalRequest {
    @NotNull(message = "o campo idPessoa é obrigatorio")
    private Long idPessoa;
    @NotNull(message = "o campo idEndereco é obrigatorio")
    private Long idEndereco;

}

