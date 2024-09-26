package com.mensal3.mensal3.dtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mensal3.mensal3.dto.ComentarioDTO;

@SpringBootTest
public class dtoTests {
    @Test
    @DisplayName("Teste para o conteúdo")
    public void testConteudo() {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        String conteudo = "Comentário esdrúxulo.";
        comentarioDTO.setConteudo(conteudo);
        
        assertEquals(conteudo, comentarioDTO.getConteudo());
    }

    @Test
    @DisplayName("Teste para o autor")
    public void testAutorId() {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        Long autorId = 1L;
        comentarioDTO.setAutorId(autorId);
        
        assertEquals(autorId, comentarioDTO.getAutorId());
    }

    @Test
    @DisplayName("Teste para o texto")
    public void testTextoId() {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        Long textoId = 2L;
        comentarioDTO.setTextoId(textoId);
        
        assertEquals(textoId, comentarioDTO.getTextoId());
    }
}
