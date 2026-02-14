package com.dotacp.counterpicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Dota2CounterPickerApplication {
	public static void main(String[] args) {
		SpringApplication.run(Dota2CounterPickerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(HeroRepository repository) {
		return (args) -> {
			// Если в базе пусто, добавим твоих героев
			if (repository.count() == 0) {
				repository.save(new Hero(null, "Terrorblade", "Agility", "Carry"));
				repository.save(new Hero(null, "Kez", "Agility", "Carry"));
				repository.save(new Hero(null, "Faceless Void", "Agility", "Carry"));
				repository.save(new Hero(null, "Luna", "Agility", "Carry"));
				repository.save(new Hero(null, "Grimstroke", "Intellect", "Support"));

				System.out.println("Твой пул героев добавлен в базу!");
			}
		};
	}
}
