package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequest;
import com.example.attornatus.api.model.dto.PessoaResponse;
import com.example.attornatus.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> findPessoaById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaResponse createResponseDTO = PessoaResponse.fromModel(pessoa);

        return new ResponseEntity<>(createResponseDTO, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<PessoaResponse> createPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = pessoaService.create(pessoaRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> updatePessoa(@PathVariable("id") Long id, @Valid @RequestBody PessoaRequest pessoaRequest) {
        pessoaRequest.setId(id);
        Pessoa pessoa = pessoaService.update(pessoaRequest);
        PessoaResponse pessoaResponse = PessoaResponse.fromModel(pessoa);
        return new ResponseEntity<>(pessoaResponse, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public Page<PessoaResponse> findPessoa(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome"));
        Page<Pessoa> pessoas = pessoaService.findAll(pageRequest);
        Page<PessoaResponse> responseDTOPage = PessoaResponse.fromModels(pessoas);
        return responseDTOPage;
    }


}
