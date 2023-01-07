package com.example.attornatus.api.repositories;

import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.id.PessoaEnderecoChave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoChave> {
}
