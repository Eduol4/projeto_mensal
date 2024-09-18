package com.mensal3.mensal3.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;
import com.mensal3.mensal3.services.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {
    
    @Autowired
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        List<UsuarioEntity> listaUsuario = new ArrayList<>();

        List<TextoEntity> textoDoSenhorTeste =  new ArrayList<>();
        List<TextoEntity> textoDaSenhoraTesta =  new ArrayList<>();

        listaUsuario.add(new UsuarioEntity(1L, "Senhor dos testes", "senha dos testes", textoDoSenhorTeste));
        listaUsuario.add(new UsuarioEntity(2L, "Senhora dos testes", "senha das testas", textoDaSenhoraTesta));

        when(usuarioRepository.findAll()).thenReturn(listaUsuario);

        UsuarioEntity usuarioEntity = new UsuarioEntity(4L, "Testudo", "password", null);
        UsuarioEntity usuarioEntity2 = new UsuarioEntity(5L, "Testuda", "passworda", null);
        when(usuarioRepository.findById(4L)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.findById(5L)).thenReturn(Optional.of(usuarioEntity2));
    }

    @Test
    @DisplayName("Teste que lista todos os usuários")
    void listarUsuariosTest() {
        List<UsuarioEntity> lista = this.usuarioService.listAllUsuario();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Teste que busca um usuário pelo Id")
    void buscarUsuarioByIdTest() {
        UsuarioEntity UserById = this.usuarioService.findById(4L);
        assertEquals("password", UserById.getSenha());
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar um usuário pelo Id")
    void buscarUsuarioByIdTestErro() {
        Exception exception = assertThrows(Exception.class, () -> {
        usuarioService.findById(22L);
    });

    assertEquals("No value present", exception.getMessage());
    }

    // @Test
    // @DisplayName("Teste que deleta um usuário pelo Id")
    // void deletarUsuarioByIdTest() {
    //     void deletedUser = this.usuarioService.deleteUsuario(4L);
    //     assert
    // }
}
