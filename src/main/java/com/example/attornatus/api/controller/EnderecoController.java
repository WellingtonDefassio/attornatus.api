package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequestDTO;
import com.example.attornatus.api.model.dto.PessoaEnderecoResponseDTO;
import com.example.attornatus.api.service.PessoaEnderecoService;
import com.example.attornatus.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController()
@RequestMapping("endereco")
public class EnderecoController {

    @Value("${dev.url}")
    private String path;
    private final String pessoaController = "/pessoa/endereco/";

    @Autowired
    private PessoaEnderecoService pessoaEnderecoService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/{id}")
    public ResponseEntity<PessoaEnderecoResponseDTO> criarEnderecoParaPessoa(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        PessoaEndereco pessoaEndereco = pessoaEnderecoService.create(id, enderecoRequestDTO);
        PessoaEnderecoResponseDTO pessoaEnderecoResponseDTO = PessoaEnderecoResponseDTO.fromModel(pessoaEndereco.getPessoa());

        URI uri = ServletUriComponentsBuilder.fromUri(URI.create(path + pessoaController))
                .path("/{id}")
                .buildAndExpand(pessoaEnderecoResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoResponseDTO> findPessoaEEnderecoById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaEnderecoResponseDTO pessoaEnderecoResponseDTO = PessoaEnderecoResponseDTO.fromModel(pessoa);

        return new ResponseEntity<>(pessoaEnderecoResponseDTO, HttpStatus.FOUND);
    }


    @GetMapping("/lista")
    public Page<PessoaEnderecoResponseDTO> findPessoasEEnderecos(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome"));
        Page<Pessoa> pessoas = pessoaService.findAll(pageRequest);
        Page<PessoaEnderecoResponseDTO> responseDTOPage = PessoaEnderecoResponseDTO.fromModels(pessoas);
        return responseDTOPage;
    }


}
