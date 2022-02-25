package com.connectbridge.connect_bridge_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화.
@SpringBootApplication
public class ConnectBridgeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectBridgeBeApplication.class, args);
	}

}
