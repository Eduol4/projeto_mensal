package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.UsuarioController;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;

@SpringBootTest
public class UsuarioControllerTests {

    @Autowired
    UsuarioController usuarioController;

    @MockBean
    UsuarioRepository usuarioRepository;

    // Mockito.when(
    //     usuarioRepository.save(new UsuarioEntity(null, "brabo").thenReturn(null));
    // )

    @Test
    @DisplayName("Função que testa a listagem de usuários")
    void testeListAllUsuario() {
        ResponseEntity<List<UsuarioEntity>> usuarios = this.usuarioController.listAllUsuario();
        assertEquals(HttpStatus.OK, usuarios.getStatusCode());
    }

    // @Test
    // void testeRegistrarUsuario() {
    //     ResponseEntity<UsuarioEntity> usuarios = usuarioController.registrarUsuario();
    //     assertEquals(HttpStatus.OK, usuarios.getStatusCode());
    // }
}