package com.mensal3.mensal3.services;

import com.mensal3.mensal3.entities.ComentarioEntity;
import com.mensal3.mensal3.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public ComentarioEntity registrarComentario(ComentarioEntity comentarioEntity) {
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

    public ComentarioEntity buscarPorId(Long idComentario) throws Exception {
        return comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new Exception("Comentário não encontrado"));
    }
}
