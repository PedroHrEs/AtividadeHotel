package com.atividade.Hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.atividade")
@EntityScan(basePackages = {"com.atividade.domains","com.atividade.domains.enums"})
@EnableJpaRepositories(basePackages = {"com.atividade.repositories"})
@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
