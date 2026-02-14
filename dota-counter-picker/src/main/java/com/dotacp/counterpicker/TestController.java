package com.dotacp.counterpicker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Сборка прошла успешно! Террорблейд, Кез, Войд и Луна в деле.";
    }
}