package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.IncorrectDateFormatException;
import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.dto.PessoaRequestDTO;
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
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PessoaServiceImpTest {
    public static final LocalDate DATA_NASCIMENTO =  LocalDate.of(1993, 8, 31);
    public static final String DATA_NASCIMENTO_STRING =  "31-08-1993";
    public static final long ID = 1l;
    public static final String NOME = "Maria";


    @InjectMocks
    private PessoaServiceImp service;
    @Mock
    private PessoaRepository pessoaRepository;

    private Pessoa pessoa;
    private Optional<Pessoa> optionalPessoa;

    private PessoaRequestDTO pessoaRequestDTO;

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

    @Test
    @DisplayName("should throw a ResourceNotFoundException when a ID is not found")
    void findByIdError() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        try {
            service.findById(ID);
        } catch (Exception e) {

            Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
            Assertions.assertEquals("pessoa não cadastrada", e.getMessage());
        }
    }

    @Test
    @DisplayName("should create a new person when all correct params is provided")
    void createSuccess() {
        Mockito.when(pessoaRepository.save(any())).thenReturn(pessoa);
        Pessoa response = service.create(pessoaRequestDTO);
        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertEquals(response, pessoa);
        Assertions.assertNotNull(response);
    }


    @Test
    @DisplayName("should throw if a incorrect email pattern is provided")
    void createEmailError() {
        Mockito.when(pessoaRepository.save(any())).thenReturn(pessoa);
        pessoaRequestDTO.setDataNascimento("31/01/1993");
        try {
            service.create(pessoaRequestDTO);
        } catch (Exception e) {
            Assertions.assertEquals(IncorrectDateFormatException.class, e.getClass());
            Assertions.assertEquals("o formato da data deve ser dd-mm-yyyy", e.getMessage());
        }

    }





    private void iniciaPessoa() {
        pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO, null);
        optionalPessoa = Optional.of(new Pessoa(ID, NOME, DATA_NASCIMENTO, null));
        pessoaRequestDTO = new PessoaRequestDTO(NOME, DATA_NASCIMENTO_STRING);
    }
}