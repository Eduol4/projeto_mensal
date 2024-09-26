package com.mensal3.mensal3.services;

import com.mensal3.mensal3.dto.ComentarioDTO;
import com.mensal3.mensal3.entities.ComentarioEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.ComentarioRepository;
import com.mensal3.mensal3.repositories.UsuarioRepository;
import com.mensal3.mensal3.repositories.TextoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TextoRepository textoRepository;

    public ComentarioEntity registrarComentario(ComentarioDTO comentarioDTO) throws Exception {
        UsuarioEntity autor = usuarioRepository.findById(comentarioDTO.getAutorId())
                .orElseThrow(() -> new Exception("Autor não encontrado"));

        TextoEntity texto = textoRepository.findById(comentarioDTO.getTextoId())
                .orElseThrow(() -> new Exception("Texto não encontrado"));

        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setConteudo(comentarioDTO.getConteudo());
        comentarioEntity.setAutor(autor);
        comentarioEntity.setTexto(texto);
        comentarioEntity.setDataComentario(LocalDateTime.now());

        return comentarioRepository.save(comentarioEntity);
    }

    public List<ComentarioEntity> listarComentarios(Long idTexto) {
        return comentarioRepository.findByTexto_IdTexto(idTexto);
    }

    public void deletarComentario(Long idComentario) throws Exception {
        ComentarioEntity comentario = comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new Exception("Comentário não encontrado"));
        comentarioRepository.delete(comentario);
    }
}
