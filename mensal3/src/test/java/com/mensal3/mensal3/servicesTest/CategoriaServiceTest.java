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
import com.mensal3.mensal3.repositories.CategoriaRepository;
import com.mensal3.mensal3.services.CategoriaService;

@SpringBootTest
public class CategoriaServiceTest {
    @MockBean
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        List<CategoriaEntity> listaCategoria = new ArrayList<>();
        List<TextoEntity> textoCategoriaTeste = new ArrayList<>();
        List<TextoEntity> textoCategoriaTeste2 = new ArrayList<>();

        listaCategoria.add(new CategoriaEntity(10L, "Título Ruim", textoCategoriaTeste));
        listaCategoria.add(new CategoriaEntity(11L, "Título Bom", textoCategoriaTeste2));
        when(categoriaRepository.findAll()).thenReturn(listaCategoria);

        CategoriaEntity categoriaEntity1 = new CategoriaEntity(12L, "Toma", null);
        CategoriaEntity categoriaEntity2 = new CategoriaEntity(13L, "Tomo", null);
        when(categoriaRepository.findById(12L)).thenReturn(Optional.of((categoriaEntity1)));
        when(categoriaRepository.findById(13L)).thenReturn(Optional.of((categoriaEntity2)));
    }

    @Test
    @DisplayName("Teste para registrar categorias")
    void registrarCategoriaTest() {
        List<TextoEntity> novoTexto =  new ArrayList<>();
        CategoriaEntity novaCategoria = new CategoriaEntity(14L, "Nova Categoria", novoTexto);
        when(categoriaRepository.save(novaCategoria)).thenReturn(novaCategoria);

        CategoriaEntity tagRegistrada = this.categoriaService.registrarCategoria(novaCategoria);
        assertEquals(novaCategoria, tagRegistrada);
    }

    @Test
    @DisplayName("Teste que lista todas as categorias")
    void listarCategoriasTest() {
        List<CategoriaEntity> lista = this.categoriaService.listAllCategoria();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Teste para deletar uma categoria pelo Id")
    void deletarCategoriaTest() throws Exception {
        categoriaService.deleteCategoria(12L);
        // verify(usuarioRepository, times(1)).delete(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Testa um erro ao tentar deletar uma categoria pelo Id")
    void deletarCategoriaTestErro() {
        Exception exception = assertThrows(Exception.class, () -> {
            categoriaService.deleteCategoria(69L);
        });

        assertEquals("Categoria não encontrada", exception.getMessage());
        // verify(usuarioRepository, times(0)).delete(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Teste para alterar uma categoria pelo Id")
    void alterarCategoriaTest() throws Exception {
        List<TextoEntity> texto = new ArrayList<>();
        CategoriaEntity categoriaExistente = new CategoriaEntity(20L, "Categoria Antiga", texto);
        CategoriaEntity novaCategoria = new CategoriaEntity(20L, "Categoria Nova", texto);

        when(categoriaRepository.findById(20L)).thenReturn(Optional.of(categoriaExistente));
        when(categoriaRepository.save(categoriaExistente)).thenReturn(novaCategoria);
    
        CategoriaEntity categoriaAlterada = categoriaService.alterarCategoria(20L, novaCategoria);
        assertEquals("Categoria Nova", categoriaAlterada.getTituloCategoria());
    
        // verify(categoriaRepository, times(1)).findById(1L);
        // verify(categoriaRepository, times(1)).save(categoriaExistente);
    }

    @Test
    @DisplayName("Testa um erro ao alterar uma Tag pelo Id")
    void alterarCategoriaTestErro() {
        when(categoriaRepository.findById(20L)).thenReturn(Optional.empty());
    
        assertThrows(Exception.class, () -> categoriaService.alterarCategoria(20L, new CategoriaEntity(20L, "Nova Categoria", null)));
    
        // verify(categoriaRepository, times(1)).findById(1L);
        // verify(categoriaRepository, never()).save(any());
    }
}
