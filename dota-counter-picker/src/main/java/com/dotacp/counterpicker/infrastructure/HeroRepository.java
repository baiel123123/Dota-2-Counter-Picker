package com.dotacp.counterpicker.infrastructure;

import com.dotacp.counterpicker.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findAllByAttribute(String attribute);
}