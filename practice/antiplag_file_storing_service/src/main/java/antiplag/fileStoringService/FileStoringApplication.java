package antiplag.fileStoringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class FileStoringApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileStoringApplication.class, args);
    }
}
