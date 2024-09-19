package com.mensal3.mensal3.controllersTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mensal3.mensal3.controllers.CategoriaController;
import com.mensal3.mensal3.repositories.CategoriaRepository;
import com.mensal3.mensal3.services.CategoriaService;

@SpringBootTest
public class CategoriaControllerTests {
    @MockBean
    CategoriaService categoriaService;

    @Autowired
    CategoriaController categoriaController;

    @MockBean
    CategoriaRepository categoriaRepository;
}
