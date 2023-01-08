package com.example.attornatus.api.controller;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;
import com.example.attornatus.api.model.dto.PessoaEnderecoResponse;
import com.example.attornatus.api.model.dto.SetPrincipalRequest;
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

    private final String pessoaController = "/pessoa/endereco/";
    @Value("${dev.url}")
    private String path;
    @Autowired
    private PessoaEnderecoService pessoaEnderecoService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/{id}")
    public ResponseEntity<PessoaEnderecoResponse> criarEnderecoParaPessoa(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnderecoRequest enderecoRequest) {

        PessoaEndereco pessoaEndereco = pessoaEnderecoService.create(id, enderecoRequest);
        PessoaEnderecoResponse pessoaEnderecoResponse = PessoaEnderecoResponse.fromModel(pessoaEndereco.getPessoa());

        URI uri = ServletUriComponentsBuilder.fromUri(URI.create(path + pessoaController))
                .path("/{id}")
                .buildAndExpand(pessoaEnderecoResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoResponse> findPessoaEEnderecoById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        PessoaEnderecoResponse pessoaEnderecoResponse = PessoaEnderecoResponse.fromModel(pessoa);

        return new ResponseEntity<>(pessoaEnderecoResponse, HttpStatus.FOUND);
    }


    @GetMapping("/lista")
    public ResponseEntity<Page<PessoaEnderecoResponse>> findPessoasEEnderecos(@RequestParam("page") int page,
                                                                              @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome"));
        Page<Pessoa> pessoas = pessoaService.findAll(pageRequest);
        Page<PessoaEnderecoResponse> responseDTOPage = PessoaEnderecoResponse.fromModels(pessoas);
        return new ResponseEntity(responseDTOPage, HttpStatus.OK);
    }

    @PostMapping("/principal")
    public ResponseEntity<?> setPrincipal(@RequestBody SetPrincipalRequest principalRequest) {

        PessoaEndereco pessoaEndereco = pessoaEnderecoService.atualizaPrincipal(principalRequest);
        PessoaEnderecoResponse pessoaEnderecoResponse = PessoaEnderecoResponse.fromModel(pessoaEndereco.getPessoa());
        return new ResponseEntity<>(pessoaEnderecoResponse, HttpStatus.OK);
    }


}
