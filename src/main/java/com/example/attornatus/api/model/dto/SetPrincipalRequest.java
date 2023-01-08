package com.example.attornatus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetPrincipalRequest {

    private Long idPessoa;
    private Long idEndereco;

}

