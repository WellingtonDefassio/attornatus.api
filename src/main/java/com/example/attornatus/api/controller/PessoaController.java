package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaResponseDTO;
import com.example.attornatus.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("{id}")
    public ResponseEntity<PessoaResponseDTO> findPessoaById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.fromModel(pessoa);

        return new ResponseEntity<>(pessoaResponseDTO, HttpStatus.OK);
    }

    
}
