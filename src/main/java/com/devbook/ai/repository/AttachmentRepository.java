package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {}
