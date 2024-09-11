package com.mensal3.mensal3.servicesTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;
import com.mensal3.mensal3.services.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {
    
    @Autowired
    UsuarioService usuarioService;

    @Test
    void teste1() {
    }
}
