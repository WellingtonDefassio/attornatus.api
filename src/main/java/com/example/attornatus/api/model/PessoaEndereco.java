package com.example.attornatus.api.model;

import com.example.attornatus.api.model.id.PessoaEnderecoChave;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEndereco {

    @EmbeddedId
    @JsonIgnore
    private PessoaEnderecoChave id;

    @ManyToOne()
    @MapsId("enderecoId")
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @MapsId("pessoaId")
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;

    private Boolean isPrincipal;


}
