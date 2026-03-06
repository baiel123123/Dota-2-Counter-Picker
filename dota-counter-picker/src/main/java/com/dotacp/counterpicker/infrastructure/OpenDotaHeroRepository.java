package com.dotacp.counterpicker.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenDotaHeroRepository extends JpaRepository<OpenDotaClient, Long> {
}
