package com.example.attornatus.api.repositories;

import com.example.attornatus.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCepAndNumero(String cep, String numero);

}
