package com.devbook.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbook.ai.entity.UsageLog;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {}
