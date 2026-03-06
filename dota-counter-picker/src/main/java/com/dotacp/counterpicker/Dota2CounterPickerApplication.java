package com.dotacp.counterpicker;

import com.dotacp.counterpicker.domain.Hero;
import com.dotacp.counterpicker.infrastructure.HeroRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Dota2CounterPickerApplication {
	public static void main(String[] args) {
		SpringApplication.run(Dota2CounterPickerApplication.class, args);
	}

}
