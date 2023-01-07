package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaEnderecoResponseDTO;
import com.example.attornatus.api.model.dto.PessoaRequestDTO;
import com.example.attornatus.api.model.dto.PessoaResponseDTO;
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

    @GetMapping("endereco/{id}")
    public ResponseEntity<PessoaEnderecoResponseDTO> findPessoaEEnderecoById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaEnderecoResponseDTO pessoaEnderecoResponseDTO = PessoaEnderecoResponseDTO.fromModel(pessoa);

        return new ResponseEntity<>(pessoaEnderecoResponseDTO, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> findPessoaById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaResponseDTO createResponseDTO = PessoaResponseDTO.fromModel(pessoa);

        return new ResponseEntity<>(createResponseDTO, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<PessoaResponseDTO> createPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = pessoaService.create(pessoaRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable("id") Long id, @Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        pessoaRequestDTO.setId(id);
        Pessoa pessoa = pessoaService.update(pessoaRequestDTO);
        PessoaResponseDTO pessoaResponseDTO = PessoaResponseDTO.fromModel(pessoa);
        return new ResponseEntity<>(pessoaResponseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/lista")
    public Page<PessoaResponseDTO> findPessoa(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome"));
        Page<Pessoa> pessoas = pessoaService.findAll(pageRequest);
        Page<PessoaResponseDTO> responseDTOPage = PessoaResponseDTO.fromModels(pessoas);
        return responseDTOPage;
    }

    @GetMapping("endereco/lista")
    public Page<PessoaEnderecoResponseDTO> findPessoasEEnderecos(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome"));
        Page<Pessoa> pessoas = pessoaService.findAll(pageRequest);
        Page<PessoaEnderecoResponseDTO> responseDTOPage = PessoaEnderecoResponseDTO.fromModels(pessoas);
        return responseDTOPage;
    }


}
