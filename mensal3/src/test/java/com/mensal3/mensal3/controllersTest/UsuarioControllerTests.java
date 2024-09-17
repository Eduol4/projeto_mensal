package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.UsuarioController;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;

@SpringBootTest
public class UsuarioControllerTests {

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

        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "Brabo", "senhaBraba", null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioEntity));
    }

    @Test
    @DisplayName("Teste que lista todos os usuários")
    void listarUsuariosTest() {
        ResponseEntity<List<UsuarioEntity>> lista = this.usuarioController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    void listarUsuariosByIdTest() {
        ResponseEntity<UsuarioEntity> UserById = this.usuarioController.findById(1L);
        assertEquals(HttpStatus.OK, UserById.getStatusCode());
        assertEquals("Brabo", UserById.getBody().getNomeUsuario());
    }

    // @Test
    // void cenario02() {
    //     ResponseEntity<List<UsuarioEntity>> lista = this.usuarioController.registrar();
    //     assertEquals(HttpStatus.OK, lista.getStatusCode());
    // }
}