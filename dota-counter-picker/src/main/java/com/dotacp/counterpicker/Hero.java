package com.dotacp.counterpicker;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Говорит Spring, что это таблица в БД
@Table(name = "heroes") // Название таблицы
@Getter // Генерирует геттеры (Lombok)
@Setter // Генерирует сеттеры (Lombok)
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
