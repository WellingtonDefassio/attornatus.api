package com.example.attornatus.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cep", "numero"})})
@Data
public class Endereco {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cidade")
    private String cidade;
}
