package org.example.InitGateway;

import org.lib.decorator.EnableTarsusGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTarsusGateway
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
