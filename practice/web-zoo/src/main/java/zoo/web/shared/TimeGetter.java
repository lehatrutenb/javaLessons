package zoo.web.shared;

import org.springframework.stereotype.Component;
import zoo.web.ishared.ItimeGetter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TimeGetter implements ItimeGetter {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
