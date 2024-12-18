package org.example.proyecto_intercambio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoIntercambioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoIntercambioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Servidor Iniciado");
    }
}
