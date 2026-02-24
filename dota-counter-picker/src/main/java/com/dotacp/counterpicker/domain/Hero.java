package com.dotacp.counterpicker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // Говорит Spring, что это таблица в БД
@Table(name = "heroes") // Название таблицы
@Data
@NoArgsConstructor // Пустой конструктор (нужен для JPA)
@AllArgsConstructor // Конструктор со всеми полями
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String attribute; // Strength, Agility, Intelligence, Universal
    private String role;      // Carry, Mid, Offlane, Support
}
