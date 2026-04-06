package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.MessageMetadata;

public interface MessageMetadataRepository extends JpaRepository<MessageMetadata, Long> {}
