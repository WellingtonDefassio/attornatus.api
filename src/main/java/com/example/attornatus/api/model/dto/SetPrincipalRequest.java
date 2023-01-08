package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetPrincipalRequest {
    @NotBlank(message = "o campo idPessoa é obrigatorio")
    private Long idPessoa;
    @NotBlank(message = "o campo idEndereco é obrigatorio")
    private Long idEndereco;

}

