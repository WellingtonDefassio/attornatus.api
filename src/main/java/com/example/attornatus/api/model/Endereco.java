package com.example.attornatus.api.model;

import com.example.attornatus.api.exception.IncorrectDateFormatException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cep", "numero"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (!id.equals(endereco.id)) return false;
        if (!cep.equals(endereco.cep)) return false;
        return numero.equals(endereco.numero);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cep.hashCode();
        result = 31 * result + numero.hashCode();
        return result;
    }

    public static void validaCep(String cep) {
        if (cep.length() != 8) {
            throw new IncorrectDateFormatException("campo cep devem conter 8 numeros");
        }
        if (!cep.matches("^[0-9]+$")) {
            throw new IncorrectDateFormatException("campo cep devem conter apenas numeros patten 00000000");
        }
    }
}
