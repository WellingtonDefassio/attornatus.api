package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.IncorrectDateFormatException;
import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;
import com.example.attornatus.api.repositories.EnderecoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class EnderecoServiceImpTest {

    public static final long ID = 1L;
    public static final String LOGRADOURO = "Rua teste 123";
    public static final String CEP = "12345678";
    public static final String NUMERO = "5432";
    public static final String CIDADE = "Londrina";
    @InjectMocks
    private EnderecoServiceImp service;

    @Mock
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;
    private EnderecoRequest enderecoRequest;
    private Optional<Endereco> optionalEndereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaEndereco();
    }

    @Test
    @DisplayName("should pass when correct data is provided")
    void createSuccess() {
        Mockito.when(enderecoRepository.
                findByCepAndNumero(Mockito.anyString(), Mockito.anyString()))
                        .thenReturn(optionalEndereco);
        Endereco response = service.create(enderecoRequest);

        Assertions.assertEquals(Endereco.class, response.getClass());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ID, response.getId());
    }

    @Test
    @DisplayName("should throw a error wen a invalid size cep is provided")
    void createCepSizeError() {
        Mockito.when(enderecoRepository.
                        findByCepAndNumero(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(optionalEndereco);
        enderecoRequest.setCep("1234567");
        try {
            service.create(enderecoRequest);
        } catch (Exception e) {
            Assertions.assertEquals(IncorrectDateFormatException.class, e.getClass());
            Assertions.assertEquals("campo cep devem conter 8 numeros", e.getMessage());
        }

    }
    @Test
    @DisplayName("should throw a error wen a invalid char cep is provided")
    void createCepCharError() {
        Mockito.when(enderecoRepository.
                        findByCepAndNumero(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(optionalEndereco);
        enderecoRequest.setCep("A123E59K");
        try {
            service.create(enderecoRequest);
        } catch (Exception e) {
            Assertions.assertEquals(IncorrectDateFormatException.class, e.getClass());
            Assertions.assertEquals("campo cep devem conter apenas numeros patten 00000000", e.getMessage());
        }

    }




    private void iniciaEndereco() {
        endereco = new Endereco(ID, LOGRADOURO, CEP, NUMERO, CIDADE);
        optionalEndereco = Optional.of(new Endereco(ID, LOGRADOURO, CEP, NUMERO, CIDADE));
        enderecoRequest = new EnderecoRequest(ID, LOGRADOURO, CEP, NUMERO,CIDADE);
    }
}