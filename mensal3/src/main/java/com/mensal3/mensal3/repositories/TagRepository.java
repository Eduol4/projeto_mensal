package com.mensal3.mensal3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mensal3.mensal3.entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}