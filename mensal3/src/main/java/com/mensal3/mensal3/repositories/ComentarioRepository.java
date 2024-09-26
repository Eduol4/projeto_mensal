package com.mensal3.mensal3.repositories;

import com.mensal3.mensal3.entities.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    List<ComentarioEntity> findByTexto_IdTexto(Long idTexto);
}
