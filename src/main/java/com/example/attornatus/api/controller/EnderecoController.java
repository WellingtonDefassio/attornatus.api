package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;
import com.example.attornatus.api.model.dto.PessoaResponseDTO;
import com.example.attornatus.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    private PessoaService pessoaService;



    @PostMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> criarEnderecoPessoa(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        Pessoa pessoa = pessoaService.findById(id);


        return null;
    }

}
