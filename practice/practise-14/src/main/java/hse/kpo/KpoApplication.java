package hse.kpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Точка входа в приложение.
 */
@EnableKafka
@SpringBootApplication
public class KpoApplication {
	public static void main(String[] args) {
		SpringApplication.run(KpoApplication.class, args);
	}
}
