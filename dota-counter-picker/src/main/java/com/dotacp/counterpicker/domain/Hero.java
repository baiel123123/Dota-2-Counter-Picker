package com.dotacp.counterpicker.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private List<String> roles;
}
