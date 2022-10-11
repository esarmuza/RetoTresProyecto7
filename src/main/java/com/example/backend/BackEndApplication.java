package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
//Con esta instruccion se le indica que cuando se ejecute donde va a buscar las Clases
//que son entidades, es decir donde buscar tablas que representan la BD
//Esta linea permite encontrar la clase deon se encuentra en main
@EntityScan(basePackages = {"com.example.backend.modelo"})
@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

}
