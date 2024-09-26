package com.mensal3.mensal3.entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.ComentarioEntity;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;

@SpringBootTest
public class EntityTests {
    
    @Test
    @DisplayName("Testa os métodos Getters e Setters das Entities")
    public void testGettersAndSetters() {
        //USUÁRIO
        UsuarioEntity usuario = new UsuarioEntity();

        Long idUsuario = 1L;
        usuario.setIdUsuario(idUsuario);
        assertEquals(idUsuario, usuario.getIdUsuario());

        String nomeUsuario = "Usuário Teste";
        usuario.setNomeUsuario(nomeUsuario);
        assertEquals(nomeUsuario, usuario.getNomeUsuario());

        String senha = "senhaTeste";
        usuario.setSenha(senha);
        assertEquals(senha, usuario.getSenha());

        List<TextoEntity> textos = new ArrayList<>();
        usuario.setTexto(textos);
        assertEquals(textos, usuario.getTexto());

        //TEXTO
        TextoEntity textoEntity = new TextoEntity();
        Long idTexto = 1L;
        textoEntity.setIdTexto(idTexto);
        assertEquals(idTexto, textoEntity.getIdTexto());

        UsuarioEntity autor = new UsuarioEntity(2L, "Autor", "senhaAutor", null);
        textoEntity.setAutor(autor);
        assertEquals(autor, textoEntity.getAutor());

        List<TagEntity> tags = new ArrayList<>();
        tags.add(new TagEntity(idTexto, senha, textos));
        tags.add(new TagEntity(idTexto, senha, textos));
        textoEntity.setTag(tags);
        assertEquals(tags, textoEntity.getTag());

        CategoriaEntity categoria = new CategoriaEntity(1L, "Categoria", null);
        textoEntity.setCategoria(categoria);
        assertEquals(categoria, textoEntity.getCategoria());

        List<ComentarioEntity> comentarios = new ArrayList<>();
        comentarios.add(new ComentarioEntity());
        comentarios.add(new ComentarioEntity());
        textoEntity.setComentarios(comentarios);
        assertEquals(comentarios, textoEntity.getComentarios());

        //TAG
        TagEntity tagEntity = new TagEntity();
        Long idTag = 1L;
        tagEntity.setIdTag(idTag);
        assertEquals(idTag, tagEntity.getIdTag());

        List<TextoEntity> textos2 = new ArrayList<>();
        tagEntity.setTexto(textos2);
        assertEquals(textos2, tagEntity.getTexto());

        //CATEGORIA
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        Long idCategoria = 1L;
        categoriaEntity.setIdCategoria(idCategoria);
        assertEquals(idCategoria, categoriaEntity.getIdCategoria());

        List<TextoEntity> textos3 = new ArrayList<>();
        categoriaEntity.setTexto(textos3);
        assertEquals(textos3, categoriaEntity.getTexto());
    }

    @Test
    @DisplayName("Teste para as entities de Comentários")
    void comentariosEntityTest() {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setId(1L);
        assertEquals(1L, comentario.getId());

        ComentarioEntity comentario1 = new ComentarioEntity();
        comentario1.setConteudo("Conteúdo de teste");
        assertEquals("Conteúdo de teste", comentario1.getConteudo());

        ComentarioEntity comentario2 = new ComentarioEntity();
        UsuarioEntity autor2 = new UsuarioEntity();
        autor2.setNomeUsuario("Autor Teste");
        comentario2.setAutor(autor2);
        assertEquals(autor2, comentario2.getAutor());

        ComentarioEntity comentario3 = new ComentarioEntity();
        TextoEntity texto2 = new TextoEntity();
        texto2.setTituloTexto("Título do Texto");
        comentario3.setTexto(texto2);
        assertEquals(texto2, comentario3.getTexto());

        ComentarioEntity comentario4 = new ComentarioEntity();
        LocalDateTime data = LocalDateTime.now();
        comentario4.setDataComentario(data);
        assertEquals(data, comentario4.getDataComentario());
    }
}
