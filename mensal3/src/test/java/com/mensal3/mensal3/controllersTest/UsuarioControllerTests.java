package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.UsuarioController;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;
import com.mensal3.mensal3.services.UsuarioService;

@SpringBootTest
public class UsuarioControllerTests {
    @MockBean
    UsuarioService usuarioService;

    @Autowired
    UsuarioController usuarioController;

    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        List<UsuarioEntity> listaUser = new ArrayList<>();
        List<TextoEntity> textoTeste =  new ArrayList<>();
        List<TextoEntity> textoTeste2 =  new ArrayList<>();
        List<TextoEntity> textoTeste3 =  new ArrayList<>();

        listaUser.add(new UsuarioEntity(1L, "Teste", "senhaTeste", textoTeste));
        listaUser.add(new UsuarioEntity(2L, "Testa", "senhoTesta", textoTeste2));
        listaUser.add(new UsuarioEntity(3L, "Senhor Teste", "senha testosa", textoTeste3));
        when(usuarioRepository.findAll()).thenReturn(listaUser);

        UsuarioEntity usuarioEntity = new UsuarioEntity(4L, "Brabo", "senhaBraba", null);
        UsuarioEntity usuarioEntity2 = new UsuarioEntity(5L, "Braba", "senhoBrabo", null);
        when(usuarioRepository.findById(4L)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.findById(5L)).thenReturn(Optional.of(usuarioEntity2));
    }

    @Test
    @DisplayName("Teste que registra um usuário")
    void registrarUsuarioControllerTest() {
        List<TextoEntity> novoTexto = new ArrayList<>();
        UsuarioEntity novoUsuario = new UsuarioEntity(6L, "Usuário Novo", "senha nova", novoTexto);
        when(usuarioService.registrarUsuario(novoUsuario)).thenReturn(novoUsuario);

        ResponseEntity<UsuarioEntity> userRegistrado = usuarioController.registrar(novoUsuario);
        assertEquals(HttpStatus.OK, userRegistrado.getStatusCode());
        assertEquals(novoUsuario, userRegistrado.getBody());
    }

    // @Test
    // @DisplayName("Testa um erro ao registrar um usuário")
    // void registrarUsuarioControllerErroTest() {
    //     List<TextoEntity> TextoRuim = new ArrayList<>();
    //     UsuarioEntity usuarioInvalido = new UsuarioEntity(6L, null, "senha ruim", TextoRuim);
    //     when(usuarioService.registrarUsuario(usuarioInvalido)).thenReturn(usuarioInvalido);

    //     ResponseEntity<UsuarioEntity> userRegistrado = usuarioController.registrar(usuarioInvalido);
    //     assertEquals(HttpStatus.BAD_REQUEST, userRegistrado.getStatusCode());
    //     assertEquals(usuarioInvalido, userRegistrado.getBody());
    // }

    @Test
    @DisplayName("Teste que lista todos os usuários")
    void listarUsersTest() {
        ResponseEntity<List<UsuarioEntity>> lista = this.usuarioController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    @DisplayName("Teste que busca um usuário por Id")
    void buscarUsersByIdTest() {
        ResponseEntity<UsuarioEntity> UserById = this.usuarioController.findById(4L);
        assertEquals(HttpStatus.OK, UserById.getStatusCode());
        assertEquals("Brabo", UserById.getBody().getNomeUsuario());
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar um usuário por Id")
    void buscarUsersByIdTestErro() {
        ResponseEntity<UsuarioEntity> UserByIdErro = this.usuarioController.findById(9L);
        assertEquals(HttpStatus.BAD_REQUEST, UserByIdErro.getStatusCode());
    }

    @Test
    @DisplayName("Teste para deletar um usuário pelo Id")
    void deletarUserByIdTest() {
        ResponseEntity<Void> deletedUser = this.usuarioController.delete(2L);
        assertEquals(HttpStatus.NO_CONTENT, deletedUser.getStatusCode());
    }

    @Test
    @DisplayName("Testa um erro ao deletar um usuário pelo Id")
    void deletarUserByIdTestErro() {
        ResponseEntity<Void> deletedUser = this.usuarioController.delete(69L);
        assertEquals(HttpStatus.BAD_REQUEST, deletedUser.getStatusCode());
    }
}