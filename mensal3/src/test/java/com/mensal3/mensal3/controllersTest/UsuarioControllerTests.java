package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.UsuarioController;
import com.mensal3.mensal3.entities.UsuarioEntity;

@SpringBootTest
public class UsuarioControllerTests {

    @Autowired
    UsuarioController usuarioController;

    @Test
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