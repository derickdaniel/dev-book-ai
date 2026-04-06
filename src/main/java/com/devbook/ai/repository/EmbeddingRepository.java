package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.Embedding;

public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {}



