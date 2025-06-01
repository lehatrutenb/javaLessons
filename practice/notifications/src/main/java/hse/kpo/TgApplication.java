package hse.kpo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Точка входа в приложение.
 */
@SpringBootApplication
@EnableScheduling
public class TgApplication {
    public static void main(String[] args) {
        SpringApplication.run(TgApplication.class, args);
    }
}
