package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EnderecoRequest {

    private Long id;
    @NotBlank(message = "o campo logradouro é obrigatorio")
    private String logradouro;
    @NotBlank(message = "o campo cep é obrigatorio")
    private String cep;
    @NotBlank(message = "o campo numero é obrigatorio")
    private String numero;
    @NotBlank(message = "o campo cidade é obrigatorio")
    private String cidade;



}
