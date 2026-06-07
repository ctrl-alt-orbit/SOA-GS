package br.com.k0.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class K0SoaApplication {
    public static void main(String[] args) {
        SpringApplication.run(K0SoaApplication.class, args);
    }
}
