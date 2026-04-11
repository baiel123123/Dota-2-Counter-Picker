package com.dotacp.counterpicker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity // Говорит Spring, что это таблица в БД
@Table(name = "heroes") // Название таблицы
@Data
@NoArgsConstructor // Пустой конструктор (нужен для JPA)
@AllArgsConstructor // Конструктор со всеми полями

public class Hero {
    @Id
    private Long id;

    private String name;

    private String localizedName;

    private String primaryAttr;

    private String attackType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hero_roles", joinColumns = @JoinColumn(name = "hero_id"))
    @Column(name = "roles")
    @Fetch(FetchMode.SUBSELECT)
    private List<String> roles;
}
