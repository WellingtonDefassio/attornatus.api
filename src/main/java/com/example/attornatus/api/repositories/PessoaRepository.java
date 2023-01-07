package com.example.attornatus.api.repositories;

import com.example.attornatus.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

   Optional<Pessoa> findByNome(String nome);

}
