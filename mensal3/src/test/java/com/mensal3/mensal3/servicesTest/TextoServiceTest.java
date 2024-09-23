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

import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TextoRepository;
import com.mensal3.mensal3.services.TextoService;

@SpringBootTest
public class TextoServiceTest {
    @Autowired
    TextoService textoService;

    @MockBean
    TextoRepository textoRepository;

    @BeforeEach
    void setUp() {
        List<TextoEntity> listaTexto = new ArrayList<>();
        UsuarioEntity autor1 = new UsuarioEntity();
        List<TagEntity> tag1 = new ArrayList<>();
        CategoriaEntity categoria1 = new CategoriaEntity();
        UsuarioEntity autor2 = new UsuarioEntity();
        List<TagEntity> tag2 = new ArrayList<>();
        CategoriaEntity categoria2 = new CategoriaEntity();
        UsuarioEntity autor3 = new UsuarioEntity();
        List<TagEntity> tag3 = new ArrayList<>();
        CategoriaEntity categoria3 = new CategoriaEntity();

        listaTexto.add(new TextoEntity(1L, "Teste", "Teste", autor1, tag1, categoria1));
        listaTexto.add(new TextoEntity(2L, "Texte", "Texte", autor2, tag2, categoria2));
        listaTexto.add(new TextoEntity(3L, "Texta", "Texta", autor3, tag3, categoria3));
        when(textoRepository.findAll()).thenReturn(listaTexto);

        TextoEntity textoEntity1 = new TextoEntity(4L, "TituloTeste", "Conteudo horrível", null, null, null);
        // when(textoRepository.findByCategoria_TituloCategoria(categoria1)).thenReturn(Optional.of(textoEntity1));
        when(textoRepository.findById(4L)).thenReturn(Optional.of(textoEntity1));
    }

    @Test
    @DisplayName("Teste para registrar textos")
    void registrarTextoTest() {
        UsuarioEntity novoAutor = new UsuarioEntity();
        List<TagEntity> novaTag = new ArrayList<>();
        CategoriaEntity NovaCategoria = new CategoriaEntity();
        TextoEntity novoTexto = new TextoEntity(5L, "Brabo", "brabinho", novoAutor, novaTag, NovaCategoria);
        when(textoRepository.save(novoTexto)).thenReturn(novoTexto);

        TextoEntity textoRegistrado = this.textoService.registrarTexto(novoTexto);
        assertEquals(novoTexto, textoRegistrado);
    }

    @Test
    @DisplayName("Teste que lista todos os textos")
    void listarUsuariosTest() {
        List<TextoEntity> lista = this.textoService.listAllTexto();
        assertEquals(3, lista.size());
    }

    @Test
    @DisplayName("Teste que busca um texto pelo Id")
    void buscarTextoByIdTest() {
        TextoEntity TextoById = this.textoService.findById(4L);
        assertEquals("TituloTeste", TextoById.getTituloTexto());
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar um texto pelo Id")
    void buscarTextoByIdTestErro() {
        Exception exception = assertThrows(Exception.class, () -> {
        textoService.findById(22L);
    });

    assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Teste para deletar um texto pelo Id")
    void deleteTextoTest() throws Exception {
        textoService.deleteTexto(4L);
        // verifytextoRepository, times(1)).delete(any(TextoEntity.class));
    }

    @Test
    @DisplayName("Testa um erro ao tentar deletar um texto pelo Id")
    void deletarTextoTestErro() {
        Exception exception = assertThrows(Exception.class, () -> {
            textoService.deleteTexto(69L);
        });

        assertEquals("Texto não encontrado!", exception.getMessage());
        // verify(usuarioRepository, times(0)).delete(any(UsuarioEntity.class));
    }
}
