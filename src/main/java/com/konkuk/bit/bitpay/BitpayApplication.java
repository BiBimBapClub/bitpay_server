package com.konkuk.bit.bitpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BitpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitpayApplication.class, args);
	}

}
