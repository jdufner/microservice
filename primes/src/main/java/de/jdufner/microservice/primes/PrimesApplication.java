package de.jdufner.microservice.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PrimesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimesApplication.class, args);
	}
}
