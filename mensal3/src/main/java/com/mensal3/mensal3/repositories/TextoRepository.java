package com.mensal3.mensal3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensal3.mensal3.entities.TextoEntity;

public interface TextoRepository extends JpaRepository<TextoEntity, Long> {
    List<TextoEntity> findByTag_TituloTag(String tagName);
    List<TextoEntity> findByCategoria_TituloCategoria(String categoriaName);
}