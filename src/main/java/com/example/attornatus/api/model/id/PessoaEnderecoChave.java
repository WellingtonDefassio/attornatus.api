package com.example.attornatus.api.model.id;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
public class PessoaEnderecoChave implements Serializable {

    @Column(name = "pessoa_id")
    private Long pessoaId;
    @Column(name = "endereco_id")
    private Long enderecoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PessoaEnderecoChave that = (PessoaEnderecoChave) o;

        if (!Objects.equals(pessoaId, that.pessoaId)) return false;
        return Objects.equals(enderecoId, that.enderecoId);
    }

    @Override
    public int hashCode() {
        int result = pessoaId != null ? pessoaId.hashCode() : 0;
        result = 31 * result + (enderecoId != null ? enderecoId.hashCode() : 0);
        return result;
    }
}
