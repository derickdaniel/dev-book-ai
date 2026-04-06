package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.AiResponse;

public interface AiResponseRepository extends JpaRepository<AiResponse, Long> {}
