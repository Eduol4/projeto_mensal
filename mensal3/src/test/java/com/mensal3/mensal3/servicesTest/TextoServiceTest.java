package com.mensal3.mensal3.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.ArgumentMatchers.any;

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
        // verify(textoRepository, times(0)).delete(any(textoEntity.class));
    }

    @Test
    @DisplayName("Teste para alterar um Texto pelo Id")
    void alterarTextoTest() throws Exception {
        TextoEntity textoVelho = new TextoEntity(15L, "Texto velho", "Conteudo velho", null, null, null);
        TextoEntity novoTexto = new TextoEntity(15L, "Texto novo", "Conteudo novo", null, null, null);

        when(textoRepository.findById(15L)).thenReturn(Optional.of(textoVelho));
        when(textoRepository.save(textoVelho)).thenReturn(novoTexto);
    
        TextoEntity textoAlterado = textoService.alterarTexto(15L, novoTexto);
        assertEquals("Texto novo", textoAlterado.getTituloTexto());
        assertEquals("Conteudo novo", textoAlterado.getConteudoTexto());
    
        // verify(textoRepository, times(1)).findById(1L);
        // verify(textoRepository, times(1)).save(textoVelho);
    }

    @Test
    @DisplayName("Testa um erro ao alterar um Texto pelo Id")
    void alterarTextoTestErro() {
        when(textoRepository.findById(20L)).thenReturn(Optional.empty());
    
        assertThrows(Exception.class, () -> textoService.alterarTexto(20L, new TextoEntity(20L, "Texto novo", "Conteudo novo", null, null, null)));
    
        // verify(textoRepository, times(1)).findById(1L);
        // verify(textoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Teste que busca textos por tag")
    void buscarTextoPorTagTest() {
        List<TextoEntity> textoByTag = new ArrayList<>();
        List<TagEntity> tags = new ArrayList<>();

        TagEntity tag = new TagEntity(1L, "Tag de Teste", new ArrayList<>());
        tags.add(tag);

        textoByTag.add(new TextoEntity(1L, "TítuloByTag1", "ConteúdoByTag1", null, tags, null));
        textoByTag.add(new TextoEntity(2L, "TítuloByTag2", "ConteúdoByTag2", null, tags, null));
        when(textoRepository.findByTag_TituloTag("Tag de Teste")).thenReturn(textoByTag);

        List<TextoEntity> textosResultado = textoService.buscarTextoTag("Tag de Teste");

        assertEquals(2, textosResultado.size());
        assertEquals("TítuloByTag1", textosResultado.get(0).getTituloTexto());
        assertEquals("TítuloByTag2", textosResultado.get(1).getTituloTexto());

        // verify(textoRepository, times(1)).findByTag_TituloTag("Tag de Teste");
    }

    @Test
    @DisplayName("Teste que busca textos por categoria")
    void buscarTextoPorCategoriaTest() {
        List<TextoEntity> textosByCategoria = new ArrayList<>();
        CategoriaEntity categorias = new CategoriaEntity(1L, "Categoria de Teste", new ArrayList<>());

        textosByCategoria.add(new TextoEntity(1L, "TítuloByCategoria1", "ConteúdoByCategoria1", null, null, categorias));
        textosByCategoria.add(new TextoEntity(2L, "TítuloByCategoria2", "ConteúdoByCategoria2", null, null, categorias));
        when(textoRepository.findByCategoria_TituloCategoria("Categoria de Teste")).thenReturn(textosByCategoria);

        List<TextoEntity> textosResultado = textoService.buscarTextoCategoria("Categoria de Teste");
        assertEquals(2, textosResultado.size());
        assertEquals("TítuloByCategoria1", textosResultado.get(0).getTituloTexto());
        assertEquals("TítuloByCategoria2", textosResultado.get(1).getTituloTexto());

        // verify(textoRepository, times(1)).findByCategoria_TituloCategoria("Categoria de Teste");
    }
}

