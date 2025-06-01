package antiplag.fileStoringService.shared;

import antiplag.fileStoringService.ishared.ItimeManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TimeManager implements ItimeManager {
    @Override
    public LocalDateTime getCurLocalDate() {
        return LocalDateTime.now();
    }
}
