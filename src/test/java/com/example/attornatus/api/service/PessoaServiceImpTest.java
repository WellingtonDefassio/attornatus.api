package com.example.attornatus.api.service;

import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.repositories.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaServiceImpTest {
    public static final LocalDate DATA_NASCIMENTO =  LocalDate.of(1993, 8, 31);
    public static final long ID = 1l;
    public static final String NOME = "Maria";


    @InjectMocks
    private PessoaServiceImp service;
    @Mock
    private PessoaRepository pessoaRepository;

    private Pessoa pessoa;
    private Optional<Pessoa> optionalPessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaPessoa();
    }

    @Test
    @DisplayName("should pass when findById return correct value")
    void findByIdSuccess() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(optionalPessoa);
        Pessoa response = service.findById(ID);

        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID, response.getId());

    }



    private void iniciaPessoa() {
        pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO, null);
        optionalPessoa = Optional.of(new Pessoa(ID, NOME, DATA_NASCIMENTO, null));
    }
}