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

import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.TagRepository;
import com.mensal3.mensal3.services.TagService;

@SpringBootTest
public class TagServiceTest {
    @MockBean
    TagRepository tagRepository;

    @Autowired
    TagService tagService;

    @BeforeEach
    void setUp() {
        List<TagEntity> listaTag = new ArrayList<>();
        List<TextoEntity> textoTagTeste = new ArrayList<>();
        List<TextoEntity> textoTagTeste2 = new ArrayList<>();

        listaTag.add(new TagEntity(10L, "Título Ruim", textoTagTeste));
        listaTag.add(new TagEntity(11L, "Título Bom", textoTagTeste2));
        when(tagRepository.findAll()).thenReturn(listaTag);

        TagEntity tagEntity1 = new TagEntity(12L, "Toma", null);
        TagEntity tagEntity2 = new TagEntity(13L, "Tomo", null);
        when(tagRepository.findById(12L)).thenReturn(Optional.of((tagEntity1)));
        when(tagRepository.findById(13L)).thenReturn(Optional.of((tagEntity2)));
    }

    @Test
    @DisplayName("Teste para registrar tags")
    void registrarTagTest() {
        List<TextoEntity> novoTexto =  new ArrayList<>();
        TagEntity novaTag = new TagEntity(14L, "Nova Tag", novoTexto);
        when(tagRepository.save(novaTag)).thenReturn(novaTag);

        TagEntity tagRegistrada = this.tagService.registrarTag(novaTag);
        assertEquals(novaTag, tagRegistrada);
    }

    @Test
    @DisplayName("Teste que lista todas as tags")
    void listarTagsTest() {
        List<TagEntity> lista = this.tagService.listAllTag();
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Teste para deletar uma tag pelo Id")
    void deletarTagTest() throws Exception {
        tagService.deleteTag(12L);
        // verify(usuarioRepository, times(1)).delete(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Testa um erro ao tentar deletar uma tag pelo Id")
    void deletarTagTestErro() {
        Exception exception = assertThrows(Exception.class, () -> {
            tagService.deleteTag(69L);
        });

        assertEquals("Tag não encontrada", exception.getMessage());
        // verify(usuarioRepository, times(0)).delete(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Teste para alterar uma Tag pelo Id")
    void alterarTagTest() throws Exception {
        List<TextoEntity> texto = new ArrayList<>();
        TagEntity tagExistente = new TagEntity(20L, "Tag Antiga", texto);
        TagEntity novaTag = new TagEntity(20L, "Tag Nova", texto);

        when(tagRepository.findById(20L)).thenReturn(Optional.of(tagExistente));
        when(tagRepository.save(tagExistente)).thenReturn(novaTag);
    
        TagEntity tagAlterada = tagService.alterarTag(20L, novaTag);
        assertEquals("Tag Nova", tagAlterada.getTituloTag());
    
        // verify(tagRepository, times(1)).findById(1L);
        // verify(tagRepository, times(1)).save(tagExistente);
    }

    @Test
    @DisplayName("Testa um erro ao alterar uma Tag pelo Id")
    void alterarTagTestErro() {
        when(tagRepository.findById(20L)).thenReturn(Optional.empty());
    
        assertThrows(Exception.class, () -> tagService.alterarTag(20L, new TagEntity(20L, "Nova Tag", null)));
    
        // verify(tagRepository, times(1)).findById(1L);
        // verify(tagRepository, never()).save(any());
}
}
