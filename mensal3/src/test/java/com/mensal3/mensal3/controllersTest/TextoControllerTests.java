package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.TextoController;
import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TextoRepository;
import com.mensal3.mensal3.services.TextoService;

@SpringBootTest
public class TextoControllerTests {
    @MockBean
    TextoService textoService;
    
    @Autowired
    TextoController textoController;

    @MockBean
    TextoRepository textoRepository;

    @BeforeEach
    void setUp() {
        List<TextoEntity> listaTexto = new ArrayList<>();
        UsuarioEntity autor1 = new UsuarioEntity();
        List<TagEntity> tag1 = new ArrayList<>();
        CategoriaEntity categoria1 = new CategoriaEntity();

        TextoEntity textoEntity1 = new TextoEntity(4L, "TituloTeste", "Conteudo horrível", autor1, tag1, categoria1);
        // when(textoRepository.findByCategoria_TituloCategoria(categoria1)).thenReturn(Optional.of(textoEntity1));
    }

    @Test
    void buscarPorCategoria() {

    }
}
