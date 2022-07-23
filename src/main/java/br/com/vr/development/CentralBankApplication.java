package br.com.vr.development;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableRabbit
@EnableMongoRepositories
@SpringBootApplication
public class CentralBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralBankApplication.class, args);
	}

}
