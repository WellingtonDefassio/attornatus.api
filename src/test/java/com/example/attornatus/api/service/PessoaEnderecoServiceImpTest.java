package com.example.attornatus.api.service;

import com.example.attornatus.api.exception.IncorrectDateFormatException;
import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.model.Endereco;
import com.example.attornatus.api.model.Pessoa;
import com.example.attornatus.api.model.PessoaEndereco;
import com.example.attornatus.api.model.dto.EnderecoRequest;
import com.example.attornatus.api.model.dto.SetPrincipalRequest;
import com.example.attornatus.api.model.id.PessoaEnderecoChave;
import com.example.attornatus.api.repositories.PessoaEnderecoRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

class PessoaEnderecoServiceImpTest {

    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(1993, 8, 31);
    public static final String DATA_NASCIMENTO_STRING = "31-08-1993";
    public static final long ID = 1l;
    public static final String NOME = "Maria";
    public static final String LOGRADOURO = "Rua teste 123";
    public static final String CEP = "12345678";
    public static final String NUMERO = "5432";
    public static final String CIDADE = "Londrina";


    @InjectMocks
    private PessoaEnderecoServiceImp service;

    @Mock
    private PessoaEnderecoRepository pessoaEnderecoRepository;
    @Mock
    private PessoaService pessoaService;
    @Mock
    private EnderecoService enderecoService;

    private PessoaEndereco pessoaEndereco;
    private EnderecoRequest enderecoRequest;
    private Pessoa pessoa;
    private Endereco endereco;
    private SetPrincipalRequest setPrincipalRequest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaDados();
    }

    @Test
    @DisplayName("should pass when create is call with correct params")
    void createSuccess() {
        Mockito.when(pessoaService.findById(Mockito.anyLong())).thenReturn(pessoa);
        Mockito.when(enderecoService.create(Mockito.any())).thenReturn(endereco);
        Mockito.when(pessoaEnderecoRepository.save(Mockito.any())).thenReturn(pessoaEndereco);

        PessoaEndereco response = service.create(ID, enderecoRequest);

        Assertions.assertEquals(PessoaEndereco.class, response.getClass());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(new PessoaEnderecoChave(ID, ID), response.getId());
    }

    @Test
    @DisplayName("should throws when a invalid person id is provided")
    void createFailPessoaID() {
        Mockito.when(pessoaService.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("pessoa não cadastrada"));
        Mockito.when(enderecoService.create(Mockito.any())).thenReturn(endereco);
        Mockito.when(pessoaEnderecoRepository.save(Mockito.any())).thenReturn(pessoaEndereco);
        try {
            service.create(ID, enderecoRequest);
        } catch (Exception e) {
            Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
            Assertions.assertEquals("pessoa não cadastrada", e.getMessage());
        }

    }

    @Test
    @DisplayName("should throws when a invalid cep is provided")
    void createFailCepEndereco() {
        Mockito.when(pessoaService.findById(Mockito.anyLong())).thenReturn(pessoa);
        Mockito.when(enderecoService.create(Mockito.any())).thenThrow(new IncorrectDateFormatException("campo cep devem conter 8 numeros"));
        Mockito.when(pessoaEnderecoRepository.save(Mockito.any())).thenReturn(pessoaEndereco);
        try {
            service.create(ID, enderecoRequest);
        } catch (Exception e) {
            Assertions.assertEquals(IncorrectDateFormatException.class, e.getClass());
            Assertions.assertEquals("campo cep devem conter 8 numeros", e.getMessage());
        }

    }

    @Test
    @DisplayName("should pass when all data is correct")
    void atualizaPrincipalSucesso() {
        Mockito.when(pessoaService.findById(Mockito.anyLong())).thenReturn(pessoa);
        Mockito.when(enderecoService.findById(Mockito.anyLong())).thenReturn(endereco);
        Mockito.when(pessoaEnderecoRepository.save(Mockito.any())).thenReturn(pessoaEndereco);
        PessoaEndereco response = service.atualizaPrincipal(setPrincipalRequest);
        Assertions.assertEquals(PessoaEndereco.class, response.getClass());
        Assertions.assertEquals(response, pessoaEndereco);
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("should throw if address not belong to the person")
    void atualizaPrincipalFailPessoaEndereco() {
        Mockito.when(pessoaService.findById(Mockito.anyLong())).thenReturn(pessoa);
        Endereco failEndereco = new Endereco(10L, LOGRADOURO, CEP, NUMERO, CIDADE);
        Mockito.when(enderecoService.findById(Mockito.anyLong())).thenReturn(failEndereco);
        Mockito.when(pessoaEnderecoRepository.save(Mockito.any())).thenReturn(pessoaEndereco);
        try {
            service.atualizaPrincipal(setPrincipalRequest);
        } catch (Exception e) {
            Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
            Assertions.assertEquals("pessoa não possui o endereço informado", e.getMessage());
        }
    }



    private void iniciaDados() {
        pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO, null);
        endereco = new Endereco(ID, LOGRADOURO, CEP, NUMERO, CIDADE);
        pessoaEndereco = new PessoaEndereco(new PessoaEnderecoChave(ID, ID), endereco, pessoa, false);
        enderecoRequest = new EnderecoRequest(ID, LOGRADOURO, CEP, NUMERO, CIDADE);
        setPrincipalRequest = new SetPrincipalRequest(ID, ID);
        pessoa.setEnderecos(List.of(pessoaEndereco));
    }

}